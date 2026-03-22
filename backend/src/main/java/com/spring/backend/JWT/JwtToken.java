package com.spring.backend.JWT;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
    
    private String jwtToken = "9f3a7c1b5e8d42a6c0f19b73e4d25a8c6b1e90af3d7c82b5f6a149c0de37ab21";

    public javax.crypto.SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtToken.getBytes());
    }

    public String jwtToken(String name){
        return Jwts.builder()
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .issuedAt(new Date(System.currentTimeMillis() + 604800000))
                .subject(name)
                .issuedAt(new Date())
                .compact();
    }

    public String extractToken(String token){
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isExp(String token){
        Date date = Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return date.before(new Date());
    }

    public boolean isValid(String token, String name){
        String uname = extractToken(token);
        return uname.equals(name) && isExp(token);
    }
}
