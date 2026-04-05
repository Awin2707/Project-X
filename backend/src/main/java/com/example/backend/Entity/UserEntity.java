package com.example.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "verify", nullable = false)
    private boolean isVerify;

    @Column(name = "isLogout", nullable = false)
    private boolean logout;

    @Column(name = "createdTime", nullable = true)
    private LocalDateTime createdTime;

    @Column(name = "LogoutTime", nullable = true)
    private LocalDateTime logoutTime;

    @Column(name = "browser")
    private String browserType;

    public UserEntity(Long uid, String email, String pass, String name, String token, boolean isVerify, boolean logout, LocalDateTime createdTime, LocalDateTime logoutTime, String browserType) {
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.token = token;
        this.isVerify = isVerify;
        this.logout = logout;
        this.createdTime = createdTime;
        this.logoutTime = logoutTime;
        this.browserType = browserType;
    }

    public UserEntity() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }
}
