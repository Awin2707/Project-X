package com.example.backend.Repo;

import com.example.backend.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByToken(String token);

}
