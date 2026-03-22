package com.spring.backend.JWT;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuth extends OncePerRequestFilter {

    @Autowired private JwtToken jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (Cookie cookie2 : cookie) {
                if (cookie2.getName().equals("u_token")) {
                    jwt = cookie2.getValue();
                }
            }
        }

        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String name = jwtToken.extractToken(jwt);
            if (!name.isEmpty() && jwtToken.isValid(jwt, name)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response);
    }

}
