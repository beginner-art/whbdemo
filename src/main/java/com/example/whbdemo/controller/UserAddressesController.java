package com.example.whbdemo.controller;

import com.example.whbdemo.dao.UserAddressesDao;
import com.example.whbdemo.domain.CartuserMsg;
import com.example.whbdemo.domain.User;
import com.example.whbdemo.domain.UserAddresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/addresses")
public class UserAddressesController {

    @Autowired
    private UserAddressesDao userAddressesDao;

    @PostMapping("/usermsg")
    public Result usermsg(@RequestBody User user){
        List<UserAddresses> userAddresses= userAddressesDao.addresserusermsg(user.getAccount());
        return new Result(Code.SAVE_OK,userAddresses);
    }

    @PostMapping("/userdel")
    public Result userdel(@RequestBody UserAddresses userAddresses ){
        userAddressesDao.deleteAddressById(userAddresses.getAddressId());
        return new Result(Code.SAVE_OK,"删除成功");
    }

    @PostMapping("/userup")
    public Result userup(@RequestBody UserAddresses userAddresses ){
        userAddressesDao.updateusermsg(userAddresses);
        return new Result(Code.SAVE_OK,"更新成功");
    }

    @PostMapping("/userist")
    public Result userist(@RequestBody UserAddresses userAddresses ){
        userAddressesDao.insertusermsg(userAddresses);
        return new Result(Code.SAVE_OK,"插入成功");
    }

}
