package com.spring.backend.Lists;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShowCategories {

    private String c_name;

    private String img;

    private String background;

    private String color;

    public ShowCategories(String c_name, String img, String background, String color) {
        this.c_name = c_name;
        this.img = img;
        this.background = background;
        this.color = color;
    }

    public ShowCategories() {
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
