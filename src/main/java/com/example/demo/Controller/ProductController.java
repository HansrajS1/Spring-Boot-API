package com.example.demo.Controller;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index() {
        return "hi";
    }

    @GetMapping("/step1")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/step2")
    public ResponseEntity<?> getProductsWithDateFilters(
            @RequestParam(required = false, name = "release_date_start") String releaseDateStart,
            @RequestParam(required = false, name = "release_date_end") String releaseDateEnd) {
        try {
            List<ProductDTO> products = service.getProductsWithDateFilters(releaseDateStart, releaseDateEnd);
            return ResponseEntity.ok(products);
        } catch (DateTimeParseException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Invalid date format. Use yyyy-MM-dd"));
        }
    }

    @GetMapping("/step3")
    public ResponseEntity<?> getProductsWithFilters(
            @RequestParam(required = false, name = "release_date_start") String releaseDateStart,
            @RequestParam(required = false, name = "release_date_end") String releaseDateEnd,
            @RequestParam(required = false, name = "brands") String brands) {
        try {
            List<ProductDTO> filteredProducts = service.getProductsByFilters(releaseDateStart, releaseDateEnd, brands);
            return ResponseEntity.ok(filteredProducts);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "Invalid date format. Use yyyy-MM-dd"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    @GetMapping("/step4")
    public ResponseEntity<?> getProductsWithFiltersAndPagination(
            @RequestParam(required = false, name = "release_date_start") String releaseDateStart,
            @RequestParam(required = false, name = "release_date_end") String releaseDateEnd,
            @RequestParam(required = false, name = "brands") String brands,
            @RequestParam(name = "page_size") Integer pageSize,
            @RequestParam(name = "page_number") Integer pageNumber) {

        try {
            if (pageSize <= 0 || pageNumber <= 0) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "page_size and page_number must be greater than 0"));
            }

            List<ProductDTO> paginated = service.getProductsByFiltersWithPagination(
                    releaseDateStart, releaseDateEnd, brands, pageSize, pageNumber);
            return ResponseEntity.ok(paginated);

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "Invalid date format. Use yyyy-MM-dd"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/step7/create")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
        try {
            ProductDTO saved = service.addProduct(product);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/step7/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO product) {
        ProductDTO updated = service.updateProduct(product, id);
        if (updated == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/step7/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        boolean deleted = service.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
