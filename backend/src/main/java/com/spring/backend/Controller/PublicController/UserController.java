package com.spring.backend.Controller.PublicController;

import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.JWT.JwtToken;
import com.spring.backend.Modal.*;
import com.spring.backend.Service.User.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/public/api/v1")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private JwtToken jwtToken;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody UserModal userModal){
        try {
            UserEntity user = userService.createAccount(userModal);
            if (user != null){
                return ResponseEntity.ok().body(Map.of("msg", user.getToken()));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",e.getMessage()));
        }
    }

    @PostMapping("/verification")
    public ResponseEntity<?> verification(@RequestBody OtpModal userModal, HttpServletResponse response){
        try {
            OtpEntity user = userService.otpVerification(userModal);
            if (user != null){
                String JWT_Token = jwtToken.jwtToken(user.getEmail());
                Cookie cookie = new Cookie("_utoken",JWT_Token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
                UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(tokens);
                return ResponseEntity.ok().body(Map.of("msg", JWT_Token));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModal userModal, HttpServletResponse response){
        try {
            UserEntity user = userService.login(userModal);
            if (user != null){
                String JWT_Token = jwtToken.jwtToken(user.getEmail());
                Cookie cookie = new Cookie("_utoken",JWT_Token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
                UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(tokens);
                return ResponseEntity.ok().body(Map.of("msg", JWT_Token));
            }
            return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",e.getMessage()));
        }
    }

    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordModal forgetPasswordModal){
        try {
            String url = userService.sendForgetPassword(forgetPasswordModal);
            if (!url.isEmpty() || url != null){
                return ResponseEntity.ok().body(Map.of("msg", "email has been sent"));
            }else {
                return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> forgetPassword(@RequestBody ResetPassword forgetPasswordModal){
        try {
            boolean url = userService.resetPassword(forgetPasswordModal);
            if (url){
                return ResponseEntity.ok().body(Map.of("msg", "Password has been reset successfully !"));
            }else {
                return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    @PostMapping("/verify-password")
    public ResponseEntity<?> verifyPassword(@RequestParam String email){
        try {
            boolean url = userService.isValidLink(email);
            if (url){
                return ResponseEntity.ok().body(Map.of("msg", "Password has been reset successfully !"));
            }else {
                return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }
}
