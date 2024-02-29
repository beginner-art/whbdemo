package com.example.whbdemo.controller;

import com.example.whbdemo.dao.ShopProductsDao;
import com.example.whbdemo.domain.ProductNameRequest;
import com.example.whbdemo.domain.ShopProducts;

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
    private static final Logger logger = LoggerFactory.getLogger(MainsearchController.class);

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

    @GetMapping
    public Result mainmsg(@RequestParam String productName){`
    }


}
