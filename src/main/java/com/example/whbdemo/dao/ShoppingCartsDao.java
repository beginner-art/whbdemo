package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.CartuserMsg;
import com.example.whbdemo.domain.ShoppingCarts;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface ShoppingCartsDao extends BaseMapper<ShoppingCarts> {
    @Select("SELECT product_id FROM products WHERE product_name = #{productName};")
    int cartproductId (String productName);

    @Select("SELECT p.product_id,sc.cart_id,p.product_name,p.price,sc.quantity,p.product_brand,p.product_image FROM shopping_carts sc JOIN user u ON sc.user_id = u.user_id JOIN products p ON sc.product_id = p.product_id WHERE u.account = #{account};")
    List<CartuserMsg> cartusermsg (String account);

    @Insert("INSERT INTO shopping_carts (user_id, product_id, quantity) VALUES (#{userId},#{productId},#{quantity})  ")
    @Options(useGeneratedKeys = true, keyProperty = "cartId", keyColumn = "cart_id")
    int istcarmsg(ShoppingCarts shoppingCarts);

    @Delete("DELETE FROM shopping_carts WHERE cart_id = #{cartId};")
    int delcarmsg(Integer cartId);

}
