//package com.webtoonmaker.api.config;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//    private static final String SECRET_KEY = "my-secret-key";
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//            .setSubject(username)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간
//            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//            .compact();
//    }
//
//    public String getUsername(String token) {
//        return Jwts.parser()
//            .setSigningKey(SECRET_KEY)
//            .build()
//            .parseClaimsJws(token)
//            .getBody()
//            .getSubject();
//    }
//}
//
