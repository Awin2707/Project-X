package com.spring.backend.Repo;

import com.spring.backend.Entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<CategoriesEntity, Long> {

    List<CategoriesEntity> findByEmail(String email);
}
