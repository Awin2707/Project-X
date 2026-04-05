package com.example.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "otp")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "Code", nullable = false)
    private String code;

    @Column(name = "browser", nullable = false)
    private String browser;

    public OtpEntity(Long id, String email, String token, String code, String browser) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.code = code;
        this.browser = browser;
    }

    public OtpEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
