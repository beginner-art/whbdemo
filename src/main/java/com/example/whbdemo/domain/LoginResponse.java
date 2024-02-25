package com.example.whbdemo.domain;
import lombok.Data;
@Data
public class LoginResponse {
    public String token;

    private User user;
    public LoginResponse(){
    }

    public LoginResponse(String token,User user){
        this.token=token;
        this.user=user;
    }
}
