package com.spring.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ForgetPasswordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "r_finger")
    private String requestFingerPrint;

    @Column(name = "s_finger")
    private String setFingerPrint;

    @Column(name = "token")
    private String token;

    @Column(name = "expire_time")
    private LocalDateTime expire_Time;

    @Column(name = "pass")
    private String password;

    @Column(name = "verified")
    private boolean isVerify;

    public ForgetPasswordEntity(Long id, String email, String requestFingerPrint, String setFingerPrint, String token, LocalDateTime expire_Time, String password, boolean isVerify) {
        this.id = id;
        this.email = email;
        this.requestFingerPrint = requestFingerPrint;
        this.setFingerPrint = setFingerPrint;
        this.token = token;
        this.expire_Time = expire_Time;
        this.password = password;
        this.isVerify = isVerify;
    }

    public ForgetPasswordEntity() {}

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

    public String getRequestFingerPrint() {
        return requestFingerPrint;
    }

    public void setRequestFingerPrint(String requestFingerPrint) {
        this.requestFingerPrint = requestFingerPrint;
    }

    public String getSetFingerPrint() {
        return setFingerPrint;
    }

    public void setSetFingerPrint(String setFingerPrint) {
        this.setFingerPrint = setFingerPrint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpire_Time() {
        return expire_Time;
    }

    public void setExpire_Time(LocalDateTime expire_Time) {
        this.expire_Time = expire_Time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
