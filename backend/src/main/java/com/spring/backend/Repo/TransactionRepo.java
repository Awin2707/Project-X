package com.spring.backend.Repo;

import com.spring.backend.Entity.IncomeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<IncomeEntity, Long> {

    @Query(value = "SELECT * FROM Transaction WHERE income = true AND email = :email", nativeQuery = true)
    List<IncomeEntity> findIncome(@Param("email") String email);

    @Query(value = "SELECT * FROM Transaction WHERE income = false AND email = :email", nativeQuery = true)
    List<IncomeEntity> findExpenses(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Transaction WHERE income = true AND email = :email AND id = :id", nativeQuery = true)
    int deleteIncomeByEmailAndId(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Transaction WHERE income = false AND email = :email AND id = :id", nativeQuery = true)
    int deleteExpensesByEmailAndId(@Param("email") String email, @Param("id") Long id);

    @Query(value = "SELECT * FROM Transaction WHERE email = :email", nativeQuery = true)
    List<IncomeEntity> listItems(@Param("email") String email);
}
