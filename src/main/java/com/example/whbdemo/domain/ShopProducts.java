package com.example.whbdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopProducts {

    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId; // 商品ID作为主键
    private String category; // 种类
    private String type; // 类型
    private String productName; // 商品名字
    private BigDecimal price; // 价格，使用BigDecimal类型以存储精确的小数
    private Integer quantity; // 数量，并设置默认值为0（在Java中，基本数据类型的默认值为0，但对于对象类型如Integer，其默认值为null。所以如果你想要确保它总是有一个值，你需要在创建对象时显式地设置它。）
    private String productImage;
    private String productBrand;

}