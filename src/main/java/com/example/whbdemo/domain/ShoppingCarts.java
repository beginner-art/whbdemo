package com.example.whbdemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ShoppingCarts {
    @TableId("cartId")
    private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Integer addedAt;

}
