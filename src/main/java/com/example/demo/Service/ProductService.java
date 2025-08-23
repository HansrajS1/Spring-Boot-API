package com.example.demo.Service;

import com.example.demo.DTO.ProductWithBrandDTO;
import com.example.demo.Model.Brand;
import com.example.demo.Model.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsWithDateFilters(String releaseDateStart, String releaseDateEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (releaseDateStart != null && releaseDateEnd != null) {
            LocalDate start = LocalDate.parse(releaseDateStart, formatter);
            LocalDate end = LocalDate.parse(releaseDateEnd, formatter);
            return productRepository.findByReleaseDateBetween(
                    java.sql.Date.valueOf(start),
                    java.sql.Date.valueOf(end));
        } else if (releaseDateStart != null) {
            LocalDate start = LocalDate.parse(releaseDateStart, formatter);
            return productRepository.findByReleaseDateGreaterThanEqual(java.sql.Date.valueOf(start));
        } else if (releaseDateEnd != null) {
            LocalDate end = LocalDate.parse(releaseDateEnd, formatter);
            return productRepository.findByReleaseDateLessThanEqual(java.sql.Date.valueOf(end));
        } else {
            return productRepository.findAll();
        }
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, int id) {
        if (productRepository.existsById(id)) {
            product.setProductId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getProductsByFilters(String releaseDateStart, String releaseDateEnd, String brands) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Product> products = productRepository.findAll();

        if (releaseDateStart != null) {
            LocalDate start = LocalDate.parse(releaseDateStart, formatter);
            products = products.stream()
                    .filter(p -> p.getReleaseDate() != null &&
                            convertToLocalDate(p.getReleaseDate()).compareTo(start) >= 0)
                    .collect(Collectors.toList());
        }

        if (releaseDateEnd != null) {
            LocalDate end = LocalDate.parse(releaseDateEnd, formatter);
            products = products.stream()
                    .filter(p -> p.getReleaseDate() != null &&
                            convertToLocalDate(p.getReleaseDate()).compareTo(end) <= 0)
                    .collect(Collectors.toList());
        }

        if (brands != null && !brands.isEmpty()) {
            Set<String> brandSet = Arrays.stream(brands.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            products = products.stream()
                    .filter(p -> p.getBrandName() != null &&
                            brandSet.contains(p.getBrandName().toLowerCase()))
                    .collect(Collectors.toList());
        }

        return products;
    }

    private LocalDate convertToLocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public List<Product> getProductsByFiltersWithPagination(
            String releaseDateStart,
            String releaseDateEnd,
            String brands,
            int pageSize,
            int pageNumber) {

        List<Product> filtered = getProductsByFilters(releaseDateStart, releaseDateEnd, brands);

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, filtered.size());

        if (fromIndex >= filtered.size()) {
            return new ArrayList<>();
        }

        return filtered.subList(fromIndex, toIndex);
    }

    public List<ProductWithBrandDTO> getJoinedProductBrandResponse(
            String releaseDateStart,
            String releaseDateEnd,
            String brands,
            int pageSize,
            int pageNumber) {

        List<Product> allProducts = getProductsByFilters(releaseDateStart, releaseDateEnd, brands);
        List<Brand> allBrands = getBrandsFromExternalAPI();

        Map<String, Brand> brandMap = allBrands.stream()
                .filter(b -> b.getName() != null)
                .collect(Collectors.toMap(
                        b -> b.getName().toLowerCase(),
                        b -> b,
                        (existing, replacement) -> existing));

        List<ProductWithBrandDTO> enriched = allProducts.stream()
                .map(product -> {
                    ProductWithBrandDTO dto = new ProductWithBrandDTO();
                    dto.setProduct_id(product.getProductId());
                    dto.setProduct_name(product.getName());
                    dto.setCategory_name(product.getCategoryName());
                    dto.setDescription_text(product.getDescriptionText());
                    dto.setPrice(product.getPrice());
                    dto.setCurrency(product.getCurrency());
                    dto.setProcessor(product.getProcessor());
                    dto.setMemory(product.getMemory());
                    dto.setAverage_rating(product.getAverageRating());
                    dto.setRating_count(product.getRatingCount());

                    if (product.getReleaseDate() != null) {
                        dto.setRelease_date(new SimpleDateFormat("yyyy-MM-dd").format(product.getReleaseDate()));
                    }

                    Brand brand = brandMap.get(product.getBrandName() == null ? "" : product.getBrandName().toLowerCase());
                    ProductWithBrandDTO.BrandDTO brandDTO = new ProductWithBrandDTO.BrandDTO();

                    if (brand != null) {
                        brandDTO.setName(brand.getName());
                        brandDTO.setYear_founded(brand.getYearFounded());
                        brandDTO.setCompany_age(Period.between(
                                LocalDate.of(brand.getYearFounded(), 1, 1),
                                LocalDate.now()).getYears());

                        String address = String.join(", ",
                                Optional.ofNullable(brand.getAddress()).map(a -> a.getStreet()).orElse(""),
                                Optional.ofNullable(brand.getAddress()).map(a -> a.getCity()).orElse(""),
                                Optional.ofNullable(brand.getAddress()).map(a -> a.getState()).orElse(""),
                                Optional.ofNullable(brand.getAddress()).map(a -> a.getPostalCode()).orElse(""),
                                Optional.ofNullable(brand.getAddress()).map(a -> a.getCountry()).orElse("")
                        ).replaceAll(", ,", ",").replaceAll(", ,", ",").trim();

                        brandDTO.setAddress(address);
                    }

                    dto.setBrand(brandDTO);
                    return dto;
                })
                .collect(Collectors.toList());

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, enriched.size());

        if (fromIndex >= enriched.size()) {
            return new ArrayList<>();
        }

        return enriched.subList(fromIndex, toIndex);
    }

    private List<Brand> getBrandsFromExternalAPI() {
        String url = "http://localhost:8080/step5";

        try {
            ResponseEntity<Brand[]> response = restTemplate.getForEntity(url, Brand[].class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Arrays.asList(response.getBody());
            } else {
                System.err.println("Failed to fetch brands. Status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Exception while calling brands API: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
