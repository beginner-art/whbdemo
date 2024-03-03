package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.Orders;
import com.example.whbdemo.domain.ProductRatings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Mapper
@Transactional
public interface ProductRatingsDao extends BaseMapper<ProductRatings> {
    @Insert("INSERT INTO product_ratings (userid, product_id, rating) VALUES (#{userId},#{productId},#{rating})")
    int insertproductrating(ProductRatings productRatings);

    @Select("SELECT * FROM product_ratings")
    List<ProductRatings> rs();

    @Select("SELECT *  FROM orders WHERE user_id = #{userId} ORDER BY purchase_time DESC LIMIT 5;")
    List<Orders> recommendProductsForUser(Integer userId);

    @Select("WITH RankedOrders AS (SELECT *, ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY purchase_time DESC) AS rn FROM orders)SELECT * FROM RankedOrders WHERE rn = 1 ORDER BY purchase_time DESC LIMIT 5;")
    List<Orders> errorrecommendProducts();
}
