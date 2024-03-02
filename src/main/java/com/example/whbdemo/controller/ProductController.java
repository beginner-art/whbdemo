package com.example.whbdemo.controller;

import com.example.whbdemo.domain.ShopProducts;
import com.example.whbdemo.util.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadProductImage(@RequestParam("productImage") MultipartFile productImage,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("productBrand") String productBrand,
                                                @RequestParam("type") String type,
                                                @RequestParam("price") BigDecimal price,
                                                @RequestParam("quantity") Integer quantity,
                                                @RequestParam("category") String category) {
        try {
            // 处理上传的文件  
            if (productImage.isEmpty()) {
                return ResponseEntity.badRequest().body("请选择一个文件上传");
            }

            // 保存文件到本地  
            String imagePath = productService.saveProductImage(productImage);

            ShopProducts shopProducts = new ShopProducts();
            shopProducts.setProductBrand(productBrand);
            shopProducts.setProductImage(imagePath);
            shopProducts.setProductName(productName);
            shopProducts.setType(type);
            shopProducts.setPrice(price);
            shopProducts.setCategory(category);
            shopProducts.setQuantity(quantity);
            // 更新数据库中的产品图片路径  
            productService.updateProductImage(shopProducts);

            return ResponseEntity.ok("图片上传成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("图片上传失败: " + e.getMessage());
        }
    }
}