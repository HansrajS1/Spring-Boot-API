package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String brandName;
    private String categoryName;
    private String descriptionText;
    private Integer price;
    private String currency;
    private String processor;
    private String memory;
    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;
    private Double averageRating;
    private Integer ratingCount;
}
