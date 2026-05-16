package com.spring.backend.Repo;

import com.spring.backend.Entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OtpEntity, Long> {

    public OtpEntity findByEmail(String email);

    public OtpEntity findByToken(String token);
}
