package com.example.whbdemo.util;

import com.example.whbdemo.dao.ShopProductsDao;
import com.example.whbdemo.domain.ShopProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ShopProductsDao shopProductsDao;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String saveProductImage(MultipartFile file) throws IOException {
        // 生成唯一的文件名  
        String fileName = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(file.getOriginalFilename());
        File dest = new File(uploadDir + "/" + fileName + "." + fileExtension);

        // 确保上传目录存在  
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        // 保存文件到目标位置  
        file.transferTo(dest);

        // 返回文件的相对路径（或你想要的任何路径格式）  
        return "/uploads/" + fileName + "." + fileExtension;
    }

    private String getFileExtension(String fileName) {
        return fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
    }

    public void updateProductImage(ShopProducts shopProducts) {
        shopProductsDao.istProductsMsg(shopProducts);

        // 这里调用MyBatis Plus的Mapper或Service来更新数据库记录  
        // 例如: productMapper.updateImage(productId, imagePath);  
        // 注意：这里只是示意，具体实现需要你根据自己的项目结构来写。  
    }
}