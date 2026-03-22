package com.spring.backend.Modal;

public class OtpModal {
    
    private String email;

    private String token;

    private String Code;

    public OtpModal(String email, String token, String code) {
        this.email = email;
        this.token = token;
        Code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    
}
