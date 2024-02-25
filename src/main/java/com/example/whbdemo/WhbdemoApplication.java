package com.example.whbdemo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.whbdemo") // 指定需要扫描的包路径

public class WhbdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhbdemoApplication.class, args);
    }

}
