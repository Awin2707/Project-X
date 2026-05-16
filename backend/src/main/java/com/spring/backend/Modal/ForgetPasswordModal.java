package com.spring.backend.Modal;

public class ForgetPasswordModal {

    private String email;
    private String fingerPrint;

    public ForgetPasswordModal(String email, String fingerPrint) {
        this.email = email;
        this.fingerPrint = fingerPrint;
    }

    public ForgetPasswordModal() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
