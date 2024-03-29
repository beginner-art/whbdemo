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

    @Select("SELECT * FROM products WHERE product_name LIKE CONCAT('%', #{productName}, '%') LIMIT 20;")
    @Results({
            @Result(column="product_name", property="productName"),
            @Result(column="category", property="category"),
            @Result(column="type", property="type"),
            @Result(column="product_brand", property="productBrand")
    })
    List<ShopProducts> mainProductsMsg(String productName);

    @Select("SELECT * FROM products LIMIT 20;")
    List<ShopProducts> allProductsMsg();
    @Select("SELECT * FROM products LIMIT 20;")
    List<ShopProducts> allProductsMsg0();
    @Insert("INSERT INTO products (category, type, product_name, price, quantity, product_image,product_brand) VALUES(#{category}, #{type}, #{productName}, #{price}, #{quantity}, #{productImage},#{productBrand})")
    int istProductsMsg(ShopProducts shopProducts);

}

