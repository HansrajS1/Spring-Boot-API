package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithBrandDTO {
    private Integer product_id;
    private String product_name;
    private BrandDTO brand;
    private String category_name;
    private String description_text;
    private Integer price;
    private String currency;
    private String processor;
    private String memory;
    private String release_date;
    private Double average_rating;
    private Integer rating_count;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandDTO {
        private String name;
        private int year_founded;
        private int company_age;
        private String address;
    }

}
