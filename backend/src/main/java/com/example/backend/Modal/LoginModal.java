package com.example.backend.Modal;

public class LoginModal {

    private String email;
    private String pass;
    private String browserType;

    public LoginModal(String email, String pass, String browserType) {
        this.email = email;
        this.pass = pass;
        this.browserType = browserType;
    }

    public LoginModal() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }
}
