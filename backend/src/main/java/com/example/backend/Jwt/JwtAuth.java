package com.example.backend.Jwt;

import com.example.backend.UserDetails.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class JwtAuth extends OncePerRequestFilter {
    @Autowired private JwtToken jwtToken;
    @Autowired private CustomUserDetails cud;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String jwt = null;
        if (cookies != null){
        for (Cookie cookie : cookies){
                if (cookie.getName().equals("_utoken")){
                    jwt = cookie.getValue();
                }
            }
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null){
            try {
                if (jwt != null) {
                String name = jwtToken.extractName(jwt);
            if (jwtToken.checkValidUser(jwt,name)){
                UserDetails ud = cud.loadUserByUsername(name);
                if (ud != null){
                    UsernamePasswordAuthenticationToken utoken = new UsernamePasswordAuthenticationToken(ud.getUsername(), null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(utoken);
                }
            }
            }
            } catch (Exception e) {
                System.out.println("jwt auth : "+e.getMessage());
                // TODO: handle exception
            }
        }
        filterChain.doFilter(request, response);
    }
}
