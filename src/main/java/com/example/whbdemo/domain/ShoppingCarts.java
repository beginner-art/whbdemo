package com.example.whbdemo.domain;

import lombok.Data;

@Data
public class ShoppingCarts {
    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Integer addedAt;

}
