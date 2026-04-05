package com.example.backend.Modal;

public class ExistsModal {
    String token;

    public ExistsModal(){}
    
    public ExistsModal(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
