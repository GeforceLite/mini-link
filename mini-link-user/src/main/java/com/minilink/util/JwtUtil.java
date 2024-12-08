package com.minilink.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/11/13 21:46
 * @Version 1.0
 * @Description: JWT工具类
 */
public class JwtUtil {
    public static final long EXPIRE = 86400000;
    public static final String JWT_SECRET = "Mini_Link_XzbWsx";

    public static String generate(String email, String nickName, String avatar) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("email", email)
                .claim("nick_name", nickName)
                .claim("avatar", avatar)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return JwtToken;
    }

    public static Map<String, Object> resolve(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String email = (String) claims.get("email");
        String nickName = (String) claims.get("nick_name");
        String avatar = (String) claims.get("avatar");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("email", email);
        resultMap.put("nick_name", nickName);
        resultMap.put("avatar", avatar);
        return resultMap;
    }
}
