package com.example.whbdemo.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class Product {
    private MultipartFile productImage;
    private String productName;
    private String type;
    private String category;
    private String productBrand;
    private BigDecimal price;
    private Integer quantity;


}
