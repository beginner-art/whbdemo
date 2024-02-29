package com.example.whbdemo.controller;


import com.example.whbdemo.dao.UserDao;
import com.example.whbdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public Result alluser() {
        List<User> allUser = userDao.getAll();
        return new Result(Code.SAVE_OK, allUser);
    }

}