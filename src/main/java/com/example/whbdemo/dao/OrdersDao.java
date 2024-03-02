package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.Orders;
import com.example.whbdemo.domain.ProductOrderCount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface OrdersDao extends BaseMapper<Orders> {
    @Insert("INSERT INTO orders (user_id, product_id, image, product_name, brand, quantity, price, status) VALUES (#{userId},#{productId},#{image},#{productName},#{brand},#{quantity},#{price},#{status});")
    int insertOrder(Orders orders);

    @Select("SELECT * FROM orders WHERE user_id = #{userId};")
    List<Orders> selectOrder (String userId);

    @Select("SELECT * FROM orders;")
    List<Orders> selectallOrder ();

    @Select("SELECT product_name, COUNT(*) as count FROM orders GROUP BY product_name ORDER BY count DESC LIMIT 20;")
    List<ProductOrderCount> statistic();

}
