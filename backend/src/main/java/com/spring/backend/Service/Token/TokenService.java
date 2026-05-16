package com.spring.backend.Service.Token;

import com.spring.backend.Interface.TokenInterface;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenService implements TokenInterface {
    @Override
    public String generateToken() {
        String value = "eXE3Xpww4w6l0tAW1Lr9XOZQu9gwbw562FIhsMbYr6jduv9VJ";
        String token = "";
        Random rnd = new Random();
        for (byte i = 0; i<12; i++){
            token += value.charAt(rnd.nextInt(value.length()));
        }
        rnd = null;
        return token;
    }

    @Override
    public String generateCode() {
        String Code = "";
        Random rnd = new Random();
        for (byte i = 0; i < 6; i++) {
            Code += rnd.nextInt(10);
        }
        rnd = null;
        return Code;
    }
}
