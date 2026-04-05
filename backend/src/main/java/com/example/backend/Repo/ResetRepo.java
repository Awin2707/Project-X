package com.example.backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.ResetEntity;

@Repository
public interface ResetRepo extends JpaRepository<ResetEntity, Long>{
    ResetEntity findByToken(String token);

    ResetEntity findByEmail(String email);
}
