package com.example.whbdemo.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.Classinformation.dao.UserDao;
//import com.Classinformation.domain.User;
//import com.Classinformation.domain.UserSecurity;
import com.example.whbdemo.dao.UserDao;
import com.example.whbdemo.domain.User;
import com.example.whbdemo.domain.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }else {
            UserSecurity userDetail = new UserSecurity(user);
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(user.getRole()));
            userDetail.setAuthorities(authorityList);
            return userDetail;

        }
    }
}