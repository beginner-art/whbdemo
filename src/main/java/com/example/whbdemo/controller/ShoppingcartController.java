package com.example.whbdemo.controller;

import com.example.whbdemo.dao.ShoppingCartsDao;
import com.example.whbdemo.domain.CartuserMsg;
import com.example.whbdemo.domain.ShoppingCarts;
import com.example.whbdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/carts")
public class ShoppingcartController {

    @Autowired
    private ShoppingCartsDao shoppingCartsDao;


    @PostMapping("/usermsg")
    public Result join(@RequestBody User user){
        List<CartuserMsg> cartusermsg= shoppingCartsDao.cartusermsg(user.getAccount());
        return new Result(Code.SAVE_OK,cartusermsg);

    }

    @PostMapping("/join")
    public Result join(@RequestBody ShoppingCarts shoppingCarts){
        shoppingCartsDao.istcarmsg(shoppingCarts);
        Integer newCartId = shoppingCarts.getCartId();
        return new Result(Code.SAVE_OK, newCartId);

    }
    @PostMapping("/del")
    public Result del(@RequestBody ShoppingCarts shoppingCarts){
        shoppingCartsDao.delcarmsg(shoppingCarts.getCartId());
        return new Result(Code.SAVE_OK, "删除成功");

    }

}
