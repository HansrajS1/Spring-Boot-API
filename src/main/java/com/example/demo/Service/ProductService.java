package com.example.demo.Service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Model.Product;
import com.example.demo.repo.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    
    public List<ProductDTO> getProductsWithDateFilters(String releaseDateStart, String releaseDateEnd) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (releaseDateStart != null && !releaseDateStart.isEmpty()) {
            startDate = LocalDate.parse(releaseDateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (releaseDateEnd != null && !releaseDateEnd.isEmpty()) {
            endDate = LocalDate.parse(releaseDateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        return productRepository.findProductsByDateFilters(startDate, endDate)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product saved = productRepository.save(product);
        return convertToDto(saved);
    }
    public ProductDTO updateProduct(ProductDTO productDTO, int id) {
        if (productRepository.existsById(id)) {
            Product product = convertToEntity(productDTO);
            product.setProductId(id);
            Product updated = productRepository.save(product);
            return convertToDto(updated);
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
    public List<ProductDTO> getProductsByFilters(String releaseDateStart, String releaseDateEnd, String brands) {
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (releaseDateStart != null && !releaseDateStart.isEmpty()) {
            startDate = LocalDate.parse(releaseDateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (releaseDateEnd != null && !releaseDateEnd.isEmpty()) {
            endDate = LocalDate.parse(releaseDateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        List<String> brandList = null;
        if (brands != null && !brands.isEmpty()) {
            brandList = List.of(brands.split(","));
        }

        return productRepository.findProductsByFilters(startDate, endDate, brandList)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByFiltersWithPagination(String releaseDateStart,
                                                               String releaseDateEnd,
                                                               String brands,
                                                               int pageSize,
                                                               int pageNumber) {
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (releaseDateStart != null && !releaseDateStart.isEmpty()) {
            startDate = LocalDate.parse(releaseDateStart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (releaseDateEnd != null && !releaseDateEnd.isEmpty()) {
            endDate = LocalDate.parse(releaseDateEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        List<String> brandList = null;
        if (brands != null && !brands.isEmpty()) {
            brandList = List.of(brands.split(","));
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return productRepository.findProductsByFiltersWithPagination(startDate, endDate, brandList, pageable)
                .getContent()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

