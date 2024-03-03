package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper

@Transactional
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user where account = #{account}")
    @Results({
            @Result(column="account", property="account"),
    })
    User getByNames(String account);

    @Select("select * from user;")
    List<User> allusers();

    @Insert("INSERT INTO user (account, username, password, phone_number, role) VALUES (#{account},#{username},#{password},#{phoneNumber},#{role}); ")
    int registeruser(User user);

    @Select("SELECT role FROM user WHERE user_id = #{userId};")
    String roleuser(User user);
}
