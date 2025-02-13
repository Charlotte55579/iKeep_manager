package com.fts.fts.fitness_tracking_system.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 密钥,用于签名和验证 JWT
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 生成 JWT
    public static String generateToken(String username, int userId) {
        return Jwts.builder()
                .setSubject(username) // 设置主题,通常是用户名
                .claim("userId", userId) // 自定义声明,存储用户 ID
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 设置过期时间,这里设置为 1 小时
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // 使用密钥和签名算法签名
                .compact(); // 生成 JWT 字符串
    }

    // 验证 JWT 并解析其中的信息
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // 设置密钥
                    .build()
                    .parseClaimsJws(token) // 解析 JWT
                    .getBody(); // 获取声明部分
        } catch (Exception e) {
            // 如果解析失败,返回 null 或者抛出自定义异常
            return null;
        }
    }
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            // 解析令牌时出现异常，说明令牌无效
            return false;
        }
    }
}
