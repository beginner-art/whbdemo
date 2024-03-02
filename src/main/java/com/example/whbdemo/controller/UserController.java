package com.example.whbdemo.controller;


import com.example.whbdemo.dao.UserDao;
import com.example.whbdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Objects;

@RestController
@Transactional
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/all")
    private Result allUser(){
        List<User> user =userDao.allusers();
        return new Result(Code.SAVE_OK,user);

    }

    @PostMapping("/logins")
    public Result logins(@RequestBody User user) {
        String account = user.getAccount();
        String password =user.getPassword();
        User alluser = userDao.getByNames(account);
        String passwords = alluser.getPassword();
        if (Objects.equals(passwords, password)){
            return new Result(Code.LOGIN_OK,alluser);

        }else {
            return new Result(Code.LOGIN_ERR,"登录失败");

        }
    }


    @PostMapping("/register")
    private Result register(@RequestBody User user){
        return new Result(Code.SAVE_OK,user);

    }

    @GetMapping("/logout")
    private Result logout(@RequestBody User user){
        return new Result(Code.SAVE_OK,user);

    }

    @PostMapping("/role")
    private Result role(@RequestBody User user){
        return new Result(Code.SAVE_OK,user);

    }
}