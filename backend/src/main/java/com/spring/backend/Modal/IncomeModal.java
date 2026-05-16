package com.spring.backend.Modal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class IncomeModal {

    private String email;
    private String amount;
    private String categories;
    private LocalDate date;
    private String notes;

    public IncomeModal(String email, String amount, String categories, LocalDate date, String notes) {
        this.email = email;
        this.amount = amount;
        this.categories = categories;
        this.date = date;
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
