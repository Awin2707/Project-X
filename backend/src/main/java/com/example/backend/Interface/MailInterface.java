package com.example.backend.Interface;

public interface MailInterface {

    void SenMail(String email, String name, String Code);

    void ResetSendMail(String token, String email);
}
