package com.example.backend.Controller.PublicController;

import com.example.backend.Base64.Base64;
import com.example.backend.Data.DataModal;
import com.example.backend.Entity.UserEntity;
import com.example.backend.Jwt.JwtToken;
import com.example.backend.Service.UserService.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/public/api/v1")
public class PublicController {

    @Autowired protected UserService userService;
    @Autowired protected Base64 base64;
    @Autowired protected JwtToken jwtToken;

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody DataModal data){
        System.out.println("yes -1");
        try{
            System.out.println("yes -2");
            UserEntity ue = userService.createAccount(data);
            if (ue != null){
                System.out.println("yes -3");
                return ResponseEntity.ok().body(Map.of("msg",base64.encodeData(ue.getToken())));
            }else {
                System.out.println("yes -4");
                return ResponseEntity.badRequest().body(Map.of("msg", Map.of("msg", base64.encodeData("Failed to connect !"))));
            }
        }catch (Exception e){
            System.out.println("yes -5");
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestBody DataModal dataModal, HttpServletResponse response){
        System.out.println("yes-0");
        try{
            System.out.println("yes-1");
            UserEntity ue = userService.otpVerification(dataModal);

            if (ue != null) {
                UsernamePasswordAuthenticationToken utoken = new UsernamePasswordAuthenticationToken(ue.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(utoken);
                String jwt = jwtToken.generateToken(ue.getEmail());
                Cookie cookie = new Cookie("_utoken", jwt);
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseEntity.ok().body(Map.of("msg", base64.encodeData(jwt)));
            }else {
                System.out.println("yes-2");
                return ResponseEntity.badRequest().body(Map.of("msg", Map.of("msg", base64.encodeData("Failed to connect !"))));
            }
        }catch (Exception e){
            System.out.println("yes-3");
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody DataModal data, HttpServletResponse response){
        try {
            UserEntity ue = userService.login(data);
            if (ue != null){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ue.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
                String jwt = jwtToken.generateToken(ue.getEmail());
                Cookie cookie = new Cookie("_utoken", jwt);
                cookie.setSecure(false);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseEntity.ok().body(Map.of("msg", base64.encodeData(jwt)));
            }else {
                return ResponseEntity.badRequest().body(Map.of("msg", Map.of("msg", base64.encodeData("Failed to connect !"))));
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }
    
    @PostMapping("/reset")
    public ResponseEntity<?> postMethodName(@RequestBody DataModal dataModal) {
        try {
            boolean value = userService.SendMail(dataModal);
            if (value) {
                return ResponseEntity.ok().body(Map.of("msg", base64.encodeData("mail have be sent")));
            }else{
                return ResponseEntity.badRequest().body(Map.of("msg", Map.of("msg", base64.encodeData("Failed to connect !"))));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }

    @GetMapping("/checkisReset")
    public ResponseEntity<?> checkisReset(@RequestBody DataModal dataModal){
        try {
            boolean value = userService.isAlreadyReset(dataModal);
            if (!value) {
                return ResponseEntity.ok().body("success");
            }else{
                return ResponseEntity.badRequest().body(Map.of("msg", base64.encodeData("failed to connect !")));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }
    
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody DataModal dataModal){
        try {
            boolean value = userService.resetPassword(dataModal);
            if (value) {
                return ResponseEntity.ok().body(Map.of("msg", base64.encodeData("password reset success")));
            }else{
                return ResponseEntity.badRequest().body(Map.of("msg", base64.encodeData("failed to connect !")));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("msg",Map.of("msg",base64.encodeData(e.getMessage()))));
        }
    }
}
