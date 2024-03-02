package com.example.whbdemo.domain;

import lombok.Data;

@Data
public class CartuserMsg {
    public Integer cartId;
    public Integer productId;
    public String productName;
    private Long price;
    private Integer Quantity;
    private String productBrand;
    private String productImage;
}
