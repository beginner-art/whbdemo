package com.example.whbdemo.config;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.whbdemo.controller.Code;
import com.example.whbdemo.controller.Result;
import com.example.whbdemo.domain.LoginResponse;
import com.example.whbdemo.domain.User;
import com.example.whbdemo.domain.UserSecurity;
import com.example.whbdemo.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/checkCode").permitAll()  //验证码
                .mvcMatchers("/users/register").permitAll() //注册
                .mvcMatchers("/users/id/**").permitAll();    //根据id查询用户是否存在
//                .anyRequest().authenticated();  //其他请求都需要认证
        http.csrf().disable(); //关闭csrf
        http.formLogin().successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                    User user = ((UserSecurity) authentication.getPrincipal()).getUser();

                    String token = TokenUtil.createToken(user);
                    Result httpResult = new Result(Code.LOGIN_OK, new LoginResponse(token, user), "登录成功");
                    String responseJson = objectMapper.writeValueAsString(httpResult);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.println(responseJson);
                    writer.flush();
                }).failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) -> {
                    Result httpResult = new Result(Code.LOGIN_ERR, null, "登录失败");
                    String responseJson = objectMapper.writeValueAsString(httpResult);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.println(responseJson);
                    writer.flush();
                }) //配置认证失败处理器
                .permitAll(); //配置登录接口
    }

//    配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

