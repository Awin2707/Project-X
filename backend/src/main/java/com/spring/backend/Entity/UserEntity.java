package com.spring.backend.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name", unique = false)
    private String name;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "verify", nullable = false)
    private boolean isVerify;

    @Column(name = "login",nullable = false)
    private LocalDateTime login;

    @Column(name = "token", nullable = false)
    private String token;

    public UserEntity(Long id, String email, String name, String pass, boolean isVerify, LocalDateTime login, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.isVerify = isVerify;
        this.login = login;
        this.token = token;
    }

    public UserEntity() {
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

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public LocalDateTime getLogin() {
        return login;
    }

    public void setLogin(LocalDateTime login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
