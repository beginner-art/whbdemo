
package com.example.whbdemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    @TableId(value = "orderId")
    private Integer orderId; // 对应order_id，但在Java中通常使用驼峰命名法
    private Integer userId; // 对应user_id
    private Integer productId; // 对应product_id
    private String image; // 对应image
    private String productName; // 对应product_name
    private String brand; // 对应brand
    private Integer quantity; // 对应quantity
    private Long price; // 对应price，使用BigDecimal处理货币更准确
    private String purchaseTime; // 对应purchase_time，使用Date类型
    private OrderStatus status; // 对应status，最好使用枚举类型来表示状态
    // 枚举类型来表示订单状态
    public enum OrderStatus {
        未发货, 已发货
    }
}