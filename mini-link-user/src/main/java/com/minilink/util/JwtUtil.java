package com.minilink.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.minilink.adapter.UserAdapter;
import com.minilink.pojo.po.MiniLinkUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author 徐志斌
 * @Date: 2023/11/13 21:46
 * @Version 1.0
 * @Description: JWT 工具类
 */
public class JwtUtil {
    public static final long EXPIRE = 7 * 24 * 60 * 60 * 1000;
    public static final String JWT_SECRET = "Mini_Link_XzbWsx";

    public static String generate(Long accountId, String email, String nickName, String avatar) {
        return Jwts.builder()
                .setSubject("Mini Link")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .claim("account_id", accountId)
                .claim("email", email)
                .claim("nick_name", nickName)
                .claim("avatar", avatar)
                .compact();
    }

    public static MiniLinkUser resolve(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Long accountId = (Long) claims.get("account_id");
        String email = (String) claims.get("email");
        String nickName = (String) claims.get("nick_name");
        String avatar = (String) claims.get("avatar");
        return UserAdapter.buildUserPO(accountId, email, avatar, nickName, null, null);
    }
}
