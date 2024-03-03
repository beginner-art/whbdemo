package com.example.whbdemo.domain;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ProductRatings {
    @TableId(value = "ratingId")
    private Integer ratingId;
    private Integer userId;
    private Integer productId;
    private Long rating;
}
