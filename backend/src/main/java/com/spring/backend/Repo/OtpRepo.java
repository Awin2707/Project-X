package com.spring.backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.backend.Entity.OtpEntity;



public interface OtpRepo extends JpaRepository<OtpEntity, Long>{
    
    OtpEntity findByCode(String code);

    OtpEntity findByToken(String token);

    OtpEntity findByEmail(String email);
}
