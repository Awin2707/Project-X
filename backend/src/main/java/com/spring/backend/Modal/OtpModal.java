package com.spring.backend.Modal;

public class OtpModal {

    private String Code;
    private String email;
    private String token;

    public OtpModal(String Code, String email, String token) {
        this.Code = Code;
        this.email = email;
        this.token = token;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
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
}
