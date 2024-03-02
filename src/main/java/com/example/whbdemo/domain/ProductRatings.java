package com.example.whbdemo.domain;
import lombok.Data;

@Data
public class ProductRatings {
    private Integer ratingId;
    private Integer userId;
    private Integer productId;
    private Long rating;
}
