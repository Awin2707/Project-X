package com.example.backend.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class Base64 {

    private static final String DEFAULT_VALUE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    @Value("${spring.key}")
    private String key;
    private String createTable(){
        Set<Character> set = new LinkedHashSet<>();
        for (char ch : key.toCharArray()){
            if (!set.contains(ch)){
                set.add(ch);
            }
        }
        for (char ch : DEFAULT_VALUE.toCharArray()){
            if (!set.contains(ch)){
                set.add(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char val: set){
            sb.append(val);
        }
        return sb.toString();
    }

    public String encodeData(String data){
        String table = createTable();
        String enc = java.util.Base64.getEncoder().encodeToString(data.getBytes());
        String res = "";
        for (char ch : enc.toCharArray()){
            int i = DEFAULT_VALUE.indexOf(ch);
            if (i != -1){
                res += table.charAt(i);
            }else {
                res += ch;
            }
        }
        return res;
    }

    public String decodeDate(String data){
        String table = createTable();
        String res = "";
        for (char ch : data.toCharArray()){
            int i = table.indexOf(ch);
            if (i != -1){
                res += DEFAULT_VALUE.charAt(i);
            }else {
                res += ch;
            }
        }
        byte[] dec = java.util.Base64.getDecoder().decode(res.getBytes());
        return new String(dec);
    }
}
