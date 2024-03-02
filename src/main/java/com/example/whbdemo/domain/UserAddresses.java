package com.example.whbdemo.domain;

import lombok.Data;


@Data
public class UserAddresses {
    private Integer addressId;
    private Integer userId;
    private String Recipient;
    private String Address;
    private String postalCode;
    private String phoneNumber;

}
