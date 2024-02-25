package com.example.whbdemo.util;

import com.example.whbdemo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * token工具类，用于生成token和验证token
 */
public class TokenUtil {
    // 密钥
    private static final String secret = "LibraryInformation";


    public static String createToken(User user) {

        long now = System.currentTimeMillis(); // 当前时间戳
        try {
            JwtBuilder builder = Jwts.builder();
            String token = builder
                    .setSubject(user.getUsername()) // 设置主题（用户名）
                    .claim("roles", user.getRole()) // 存储用户权限信息
                    .setIssuedAt(new Date(now))// 设置签发时间
                    .setExpiration(new Date(System.currentTimeMillis() + (long) (1000 * 60 * 60))) // 设置过期时间1h
                    .signWith(SignatureAlgorithm.HS256, secret) // 设置签名算法和密钥
                    .compact();
            return token;

        } catch (Exception e) {
            System.out.println(e);
            return "Flase";
        }
    }


    public static String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static String getRolesFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return (String) claims.get("roles");
        } catch (Exception e) {
            return null;
        }
    }
}
