package com.spring.backend.Entity;

import com.spring.backend.Lists.AddCategories;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "c_list")
    @ElementCollection
    private List<AddCategories> categories;

    public CategoriesEntity(Long id, String email, List<AddCategories> categories) {
        this.id = id;
        this.email = email;
        this.categories = categories;
    }

    public CategoriesEntity() {
    }

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

    public List<AddCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<AddCategories> categories) {
        this.categories = categories;
    }
}

