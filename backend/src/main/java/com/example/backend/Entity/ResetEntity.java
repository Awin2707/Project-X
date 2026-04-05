package com.example.backend.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reset")
public class ResetEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false, name = "expTime")
    private LocalDateTime isExperiedToken;

    @Column(nullable = false, name = "reseted")
    private boolean passReset;

    public ResetEntity(){}

    public ResetEntity(Long id, String email, String token, LocalDateTime isExperiedToken, boolean passReset) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.isExperiedToken = isExperiedToken;
        this.passReset = passReset;
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

    public LocalDateTime getIsExperiedToken() {
        return isExperiedToken;
    }

    public void setIsExperiedToken(LocalDateTime isExperiedToken) {
        this.isExperiedToken = isExperiedToken;
    }

    public boolean isPassReset() {
        return passReset;
    }

    public void setPassReset(boolean passReset) {
        this.passReset = passReset;
    }

    
}
