package com.example.backend.Controller.PrivateController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/private/api/v1")
public class PrivateController {
    
    @GetMapping("/version")
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.ok("hello new world !");
    }
    
}
