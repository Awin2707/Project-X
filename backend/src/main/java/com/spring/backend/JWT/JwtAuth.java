package com.spring.backend.JWT;

import com.spring.backend.UserDetails.CustomUserDetails;
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

@Service
public class JwtAuth extends OncePerRequestFilter {

    @Autowired private JwtToken jwtToken;
    @Autowired private CustomUserDetails customUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/public")){
            filterChain.doFilter(request, response);
            return;
        }
        String JWT = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("_utoken")){
                    JWT = cookie.getValue();
                }
            }
        }

        if (JWT != null && SecurityContextHolder.getContext().getAuthentication() == null){
            String name = jwtToken.extractUserName(JWT);
            if (name != null && jwtToken.verifyingUser(JWT, name)){
                UserDetails ud = customUserDetails.loadUserByUsername(name);
                if (ud != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
