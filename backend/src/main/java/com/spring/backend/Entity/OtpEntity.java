package com.spring.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "code")
    private String code;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = true, name = "token")
    private String token;

    @Column(nullable = false, name = "otpValid")
    private LocalDateTime optValid;

    public OtpEntity(Long id, String code, String email, String token, LocalDateTime otpValid) {
        this.id = id;
        this.code = code;
        this.email = email;
        this.token = token;
        this.optValid = otpValid;
    }

    public LocalDateTime getOptValid() {
        return optValid;
    }

    public void setOptValid(LocalDateTime optValid) {
        this.optValid = optValid;
    }

    public OtpEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
