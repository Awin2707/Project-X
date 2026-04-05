package com.example.backend.Repo;

import com.example.backend.Entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OtpEntity, Long> {

    OtpEntity findByEmail(String email);
}
