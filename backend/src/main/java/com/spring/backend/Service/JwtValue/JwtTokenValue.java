package com.spring.backend.Service.JwtValue;

import com.spring.backend.JWT.JwtToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenValue {

    @Autowired private JwtToken jwtToken;

    public String extractEmail(HttpServletRequest request){
        String jwt = null;
        Cookie[] cookie = request.getCookies();
        if (cookie != null){
            for (Cookie cook : cookie){
                if (cook.getName().equals("_utoken")){
                    jwt = cook.getValue();
                }
            }
        }
        String name = null;
        name = jwtToken.extractUserName(jwt);
        if (name != null){
            return name;
        }
        return null;
    }
}
