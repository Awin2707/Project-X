package com.spring.backend.Modal;

public class ResetPassword {

    private String token;
    private String setFingerPrint;
    private String password;

    public ResetPassword(String token, String setFingerPrint, String password) {
        this.token = token;
        this.setFingerPrint = setFingerPrint;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSetFingerPrint() {
        return setFingerPrint;
    }

    public void setSetFingerPrint(String setFingerPrint) {
        this.setFingerPrint = setFingerPrint;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
