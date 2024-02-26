package com.example.whbdemo.controller;

import com.example.whbdemo.dao.ShopProductsDao;
import com.example.whbdemo.domain.ShopProducts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/products")
public class ShopProductsController {
    private static final Logger logger = LoggerFactory.getLogger(MainsearchController.class);

    @Autowired
    private ShopProductsDao shopProductsDao;

    @GetMapping("/search")
    public Result search(@RequestParam String searchQuery) {
        ShopProducts shopProducts = new ShopProducts();
        shopProducts.setProductName(searchQuery);
        shopProductsDao.aa();

        return new Result(Code.SAVE_OK , searchQuery);
    }

}
