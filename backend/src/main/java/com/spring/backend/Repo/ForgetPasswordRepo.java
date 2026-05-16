package com.spring.backend.Repo;

import com.spring.backend.Entity.ForgetPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgetPasswordRepo extends JpaRepository<com.spring.backend.Entity.ForgetPasswordEntity, Long> {

    ForgetPasswordEntity findByEmail(String email);
    ForgetPasswordEntity findByToken(String token);
}
