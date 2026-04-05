package com.example.backend.Service.Token;

import com.example.backend.Interface.TokenInterface;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenService implements TokenInterface {
    @Override
    public String generateToken() {
        String token = "badhugey9777231cghdgasudjpaka21-kkjsbdgqwax";
        String res = "";
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) {
            res += token.charAt(rnd.nextInt(token.length()));
        }
        rnd = null;
        return res;
    }

    @Override
    public String generateOtp() {
        String res = "";
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            res += rnd.nextInt(10);
        }
        rnd = null;
        return res;
    }
}
