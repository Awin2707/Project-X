package com.spring.backend.Service.Base64;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class Base64 {

    public final static String DEFAULT_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public String createTable(String Key) {
        Set<Character> list = new TreeSet<>();
        for (Character character : Key.toCharArray()) {
            list.add(character);
        }
        for (Character character : DEFAULT_STRING.toCharArray()) {
            list.add(character);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : list) {
            sb.append(character);
        }

        return sb.toString();
    }

    public String encode(String json, String key){
        String table = createTable(key);
        String encode_data = java.util.Base64.getEncoder().encodeToString(json.getBytes());
        StringBuilder encode = new StringBuilder();
        for (Character ch : encode_data.toCharArray()) {
            int i = DEFAULT_STRING.indexOf(ch);
            if (i != -1) {
                encode.append(table.charAt(i));
            }else{
                encode.append(ch);
            }
        }
        return encode.toString();
    }

    public String decode(String json, String key){
        String table = createTable(key);
        StringBuilder decode = new StringBuilder();
        for (Character ch : json.toCharArray()) {
            int i = DEFAULT_STRING.indexOf(ch);
            if (i != -1) {
                decode.append(table.charAt(i));
            }else{
                decode.append(ch);
            }
        }
        byte[] decode_Byte = java.util.Base64.getDecoder().decode(decode.toString());
        return decode_Byte.toString();
    }
}
