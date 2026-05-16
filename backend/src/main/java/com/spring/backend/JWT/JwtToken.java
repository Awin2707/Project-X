package com.spring.backend.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtToken {
    @Value("${spring.jwt}")
    private String jwt;

    private SecretKey keyGenerate(){
        return Keys.hmacShaKeyFor(jwt.getBytes());
    }

    public String jwtToken(String email) {
        return Jwts.builder()
                .signWith(keyGenerate(), SignatureAlgorithm.HS256)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 7 * 60 * 60 * 60))
                .compact();
    }

    public String extractUserName(String Token) {
        return Jwts.parserBuilder()
                .setSigningKey(keyGenerate())
                .build()
                .parseClaimsJws(Token)
                .getBody()
                .getSubject();
    }

    public boolean isVerify(String Token){
        Date date = Jwts.parserBuilder()
                .setSigningKey(keyGenerate())
                .build()
                .parseClaimsJws(Token)
                .getBody()
                .getExpiration();
        return  date.after(new Date());
    }

    public boolean verifyingUser(String Token, String userName){
        String name = extractUserName(Token);
        return userName.equals(name) && isVerify(Token);
    }
}
