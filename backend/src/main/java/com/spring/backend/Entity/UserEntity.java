package com.spring.backend.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "isLogin", nullable = false)
    private boolean isLogin;

    @Column(name = "loginDate")
    private LocalDateTime localDateTime;

    @Column(name = "logout")
    private boolean isLogout;

    @Column(name = "token")
    private String utoken;

    @Column(name = "isVerify", nullable = false)
    private boolean isVerify;

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String name, String pass, boolean isLogin, LocalDateTime localDateTime,
            boolean isLogout, String utoken, boolean isVerify) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.isLogin = isLogin;
        this.localDateTime = localDateTime;
        this.isLogout = isLogout;
        this.utoken = utoken;
        this.isVerify = isVerify;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean isVerify) {
        this.isVerify = isVerify;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isLogout() {
        return isLogout;
    }

    public void setLogout(boolean isLogout) {
        this.isLogout = isLogout;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    
}
