package com.spring.backend.Service.Token;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateToken {
    
    public String Token = "fasdr67231feysvavdvuac8nsuarw6rq7gdq";

    public String generateToken(){
        String token = "";
        Random rnd = new Random();
        byte len = (byte) Token.length();
        for (int i = 0; i < 12; i++) {
            token += Token.charAt(rnd.nextInt(len));
        }
        return token;
    }
}
