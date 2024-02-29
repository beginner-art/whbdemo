package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.ShopProducts;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface ShopProductsDao extends BaseMapper<ShopProducts> {
    @Select("SELECT product_name FROM products WHERE product_name LIKE CONCAT('%', #{productName}, '%') ORDER BY product_name LIMIT 5;")

    List<String> searchProductsByName(String productName);
}
