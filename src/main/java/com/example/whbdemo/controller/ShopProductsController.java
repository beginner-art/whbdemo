package com.example.whbdemo.controller;

import com.example.whbdemo.dao.ShopProductsDao;
import com.example.whbdemo.domain.ProductNameRequest;

import com.example.whbdemo.domain.ShopProducts;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/products")
public class ShopProductsController {

    @Autowired
    private ShopProductsDao shopProductsDao;

    @PostMapping("/search")
    public Result search(@RequestBody ProductNameRequest request) {
        List<String> productNames = shopProductsDao.searchProductsByName(request.getProductName());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Rows", productNames);
        responseBody.put("Total", productNames.size());
        return new Result(Code.SAVE_OK , responseBody);
    }

    @PostMapping("/mainmsg")  //渲染商品页面
    public Result mainmsg(@RequestBody ShopProducts productName){

        List<ShopProducts> productsMsg = shopProductsDao.mainProductsMsg(productName.getProductName());
        Map<String, Object> responseMsg = new HashMap<>();
        responseMsg.put("Rows", productsMsg);

        return  new Result(Code.SAVE_OK , responseMsg);

    }

    @GetMapping("/all")
    public Result allmsg(@RequestBody ShopProducts productName){
        List<ShopProducts> productsAll = shopProductsDao.allProductsMsg();
        return  new Result(Code.SAVE_OK , productsAll);
    }



//    @PostMapping("/main0msg")
//    public Result main0msg(@RequestBody ShopProducts productsMsg){
//        if (productsMsg.getProductBrand()!=""){
//
//        }
//        System.out.println(productsMsg.getProductBrand());
//        return new Result(Code.SAVE_OK , productsMsg.getProductBrand());
//    }


}
