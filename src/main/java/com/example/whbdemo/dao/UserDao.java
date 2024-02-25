package com.example.whbdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.whbdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface UserDao extends BaseMapper<User> {

    @Select("select uid,uname from user WHERE deleted=0; ")
    List<User> getAll();


    @Select("select * from user where uid = #{uid}")
    User getById(String uid);
}
