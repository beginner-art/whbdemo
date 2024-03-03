package com.example.whbdemo.controller;

import com.example.whbdemo.dao.OrdersDao;
import com.example.whbdemo.domain.Orders;
import com.example.whbdemo.domain.ProductOrderCount;
import com.example.whbdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersDao ordersDao;

    @PostMapping("/instepOrder")
    public Result instepOrder(@RequestBody Orders orders){
        int insertOrder = ordersDao.insertOrder(orders);
        return new Result(Code.SAVE_OK , "插入成功");
    }

    @PostMapping("/usermsg")
    public Result usermsg(@RequestBody User user){
        List<Orders> selectOrder = ordersDao.selectOrder(user.getUserId());
        return new Result(Code.SAVE_OK , selectOrder);
    }

    @PostMapping("/allmsg")
    public Result allmsg(){
        List<Orders> selectOrder = ordersDao.selectallOrder();
        return new Result(Code.SAVE_OK , selectOrder);
    }

    @PostMapping("/statistic")
    public Result statistic(){
        List<ProductOrderCount> selectOrder = ordersDao.statistic();
        return new Result(Code.SAVE_OK , selectOrder);
    }

    @PostMapping("/sellermsg")
    public Result sellermsg(@RequestBody User user){
        List<Orders> selectsellerOrder = ordersDao.selectsellerOrder(user.getUsername());
        return new Result(Code.SAVE_OK , selectsellerOrder);
    }

    @PostMapping("/sellership")
    public Result sellership(@RequestBody Orders orders){
        ordersDao.updatesellerOrder(orders.getOrderId());
        return new Result(Code.SAVE_OK , "更新成功");
    }
}
