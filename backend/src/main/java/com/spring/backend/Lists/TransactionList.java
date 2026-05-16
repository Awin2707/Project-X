package com.spring.backend.Lists;

import java.time.LocalDateTime;

public class TransactionList {

    private Long id;

    private String amount;

    private String categories;

    private LocalDateTime localDateTime;

    private String Notes;

    public TransactionList(Long id, String amount, String categories, LocalDateTime localDateTime, String notes) {
        this.id = id;
        this.amount = amount;
        this.categories = categories;
        this.localDateTime = localDateTime;
        Notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
