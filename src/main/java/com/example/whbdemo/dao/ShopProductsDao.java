package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.ShopProducts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface ShopProductsDao extends BaseMapper<ShopProducts> {
    @Select("SELECT product_name FROM products WHERE product_name LIKE CONCAT('%', 'Co', '%') ORDER BY product_name LIMIT 10;")
    List<ShopProducts> aa();
}
