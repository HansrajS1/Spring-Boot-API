package com.example.demo.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int productId;
    private String name;
    private String brand;
    private String categoryName;
    private String descriptionText;
    private double price;
    private String currency;
    private String processor;
    private String memory;
    private LocalDate releaseDate;
    private double averageRating;
    private int ratingCount;
}
