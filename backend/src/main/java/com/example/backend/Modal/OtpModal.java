package com.example.backend.Modal;

public class OtpModal {

    private String Code;
    private String email;
    private String token;
    private String browser;

    public OtpModal(String code, String email, String token, String browser) {
        Code = code;
        this.email = email;
        this.token = token;
        this.browser = browser;
    }

    public OtpModal() {
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

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
