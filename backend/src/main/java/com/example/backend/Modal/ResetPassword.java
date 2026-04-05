package com.example.backend.Modal;

public class ResetPassword {
    
    String password;

    String newPassword;

    String token;

    public ResetPassword() {
    }

    public ResetPassword(String password, String newPassword, String token) {
        this.password = password;
        this.newPassword = newPassword;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
