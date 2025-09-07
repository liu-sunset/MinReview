package peng.zhi.liu.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    public static String createJWT(String secretkey, long ttlMillis, Map<String, Object> claims){
        //选择一种签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date date = new Date(System.currentTimeMillis() + ttlMillis);
        Date now = new Date();
        //生成jwt令牌
        String jwt = Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(now)//设置iat
                .setExpiration(date)
                .signWith(signatureAlgorithm,secretkey.getBytes(StandardCharsets.UTF_8))
                .compact();
        return jwt;
    }

    public static Claims parseJWT(String secretKey,String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}