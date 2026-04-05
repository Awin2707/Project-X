package com.example.backend.Jwt;

import com.example.backend.Base64.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtToken {

    @Autowired private Base64 base64;

    @Value("${spring.jwt}")
    private String jwt;

    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwt.getBytes());
    }

    public String generateToken(String email){
        String encode = base64.encodeData(email);
        return Jwts.builder()
                .signWith(secretKey())
                .subject(encode)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 7 * 1000 * 60 * 60 * 24))
                .compact();
    }

    public String extractName(String token){
        String name = Jwts.parser()
                .setSigningKey(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return base64.decodeDate(name);
    }

    public boolean isExpired(String token){
        Date date = Jwts.parser()
                .setSigningKey(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return date.after(new Date());
    }

    public boolean checkValidUser(String token, String name){
        return isExpired(token) && extractName(token).equals(name);
    }
}
