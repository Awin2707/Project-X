package com.spring.backend.Entity;

import com.spring.backend.Lists.AddCategories;
import com.spring.backend.Lists.ShowCategories;
import com.spring.backend.Lists.TransactionList;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class IncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean income;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = true)
    private ShowCategories categories;

//    @Column(nullable = false)
    private LocalDate localDateTime;

//    @Column(nullable = false)
    private String notes;

    public IncomeEntity(Long id, String email, boolean income, String amount, ShowCategories categories, LocalDate localDateTime, String notes) {
        this.id = id;
        this.email = email;
        this.income = income;
        this.amount = amount;
        this.categories = categories;
        this.localDateTime = localDateTime;
        this.notes = notes;
    }

    public IncomeEntity(){}

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

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ShowCategories getCategories() {
        return categories;
    }

    public void setCategories(ShowCategories categories) {
        this.categories = categories;
    }

    public LocalDate getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDate localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
