package com.spring.backend.Controller.PrivateController;

import com.spring.backend.Repo.TransactionRepo;
import com.spring.backend.Service.JwtValue.JwtTokenValue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/private/api/v1")
public class PrivateControl {

    @Autowired private JwtTokenValue jwtTokenValue;
    @Autowired private TransactionRepo transactionRepo;

    @GetMapping("/version")
    public ResponseEntity<?> getResponse(){
        return ResponseEntity.ok().body(Map.of("version" , "1.0.0"));
    }

    @GetMapping("/listItems")
    public ResponseEntity<?> getListItems(HttpServletRequest request){
        String email = jwtTokenValue.extractEmail(request);
        if (email != null){
            return ResponseEntity.ok().body(Map.of("msg", transactionRepo.listItems(email)));
        }
        return ResponseEntity.badRequest().body(Map.of("msg", "failed !"));
    }
}
