package com.spring.backend.Controller.PublicController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.Data.DataModal;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.JWT.JwtToken;
import com.spring.backend.Service.Base64.Base64;
import com.spring.backend.Service.UserService.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/public/api/v1")
public class PublicController {

    @Autowired private UserService us;
    @Autowired private Base64 base64;
    @Autowired AuthenticationManager authentication;
    @Autowired private JwtToken jToken;
    
    @GetMapping("/version")
    public ResponseEntity<?> getMethodName() {
        System.out.println(base64.createTable("myKeySecret"));
        return ResponseEntity.ok("1.0.0");
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody DataModal data) {
        System.out.println("hello");
        try {
            UserEntity user = us.createAccount(data);
            if (user != null) {
                String json = "{ \"message\": \"" + user.getUtoken() + "\" }";
                String msg = base64.encode(json, "myKeySecret");
                ResponseEntity.ok().body(msg);
            }else{
                ResponseEntity.badRequest().body(base64.encode("failed !","myKeySecret"));
            }
        } catch (Exception e) {
            String json = "{message :" + e.getMessage()+" }";
            String msg = base64.encode(json, "myKeySecret");
            ResponseEntity.ok().body(msg);
        }
        return null;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestBody DataModal data, HttpServletResponse response) {
        try {
            UserEntity user = us.otpVerification(data);
            if (user != null) {
                Authentication auth = authentication.authenticate( new
                    UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>())
                );
                String Token = jToken.jwtToken(user.getEmail());
                SecurityContextHolder.getContext().setAuthentication(auth);
                Cookie cookie = new Cookie("u_token", Token);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(7 * 24 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
                String json = "{message :" + Token +" }";
                String msg = base64.encode(json, "myKeySecret");
                System.out.println(msg);
                return ResponseEntity.ok().body(msg);
            }else{
                System.out.println(base64.encode("failed !","myKeySecret"));
                return ResponseEntity.badRequest().body(base64.encode("failed !","myKeySecret"));
            }
        } catch (Exception e) {
            String json = "{message :" + e.getMessage()+" }";
            String msg = base64.encode(json, "myKeySecret");
            System.out.println(msg);
            return ResponseEntity.ok().body(msg);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody DataModal data, HttpServletResponse response) {
        try {
            UserEntity user = us.Login(data);
            if (user != null) {
                Authentication auth = authentication.authenticate( new
                    UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>())
                );
                String Token = jToken.jwtToken(user.getEmail());
                SecurityContextHolder.getContext().setAuthentication(auth);
                Cookie cookie = new Cookie("u_token", Token);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(7 * 24 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
                String json = "{message :" + Token +" }";
                String msg = base64.encode(json, "myKeySecret");
                return ResponseEntity.ok().body(msg);
            }else{
                return ResponseEntity.badRequest().body(base64.encode("failed !","myKeySecret"));
            }
        } catch (Exception e) {
            String json = "{message :" + e.getMessage()+" }";
            String msg = base64.encode(json, "myKeySecret");
            return ResponseEntity.ok().body(msg);
        }
    }
    
}
