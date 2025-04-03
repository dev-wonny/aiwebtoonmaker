package com.webtoonmaker.api.chat.infra.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.security.Key;


@Component
public class JwtUtil {
    private final Key secretKey;

    public JwtUtil(@Value("${service.jwt.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public Claims getClaims(String token) {
        return Jwts.parser()  //최신 버전에서 변경됨
            .verifyWith((SecretKey) secretKey) //시그니처 검증 방식 변경
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

//    public Claims getClaims(String token) {
//        return Jwts.parserBuilder()// 없음
//            .setSigningKey(secretKey)
//            .build()
//            .parseClaimsJws(token)
//            .getBody();
//    }
}

