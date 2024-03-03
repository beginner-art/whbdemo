package com.example.whbdemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class UserAddresses {
    @TableId("addressId")
    private Integer addressId;
    private Integer userId;
    private String Recipient;
    private String Address;
    private String postalCode;
    private String phoneNumber;

}
