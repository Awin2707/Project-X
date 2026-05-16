package com.spring.backend.Lists;

import jakarta.persistence.Embeddable;

@Embeddable
public class AddCategories {

    private long id;

    private String c_name;

    private String img;

    private String color;

    private String background;

    public AddCategories(Long id, String c_name, String img, String background, String color) {
        this.id = id;
        this.c_name = c_name;
        this.img = img;
        this.background = background;
        this.color = color;
    }

    public AddCategories() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

