package com.spring.backend.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.spring.backend.Entity.UserEntity;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    
    UserEntity findByEmail(String email);

    UserEntity findByUtoken(String utoken);
}
