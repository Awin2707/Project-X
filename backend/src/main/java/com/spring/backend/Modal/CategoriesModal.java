package com.spring.backend.Modal;

public class CategoriesModal {

    private String name;
    private String img;
    private String email;
    private String background;
    private String color;

    public CategoriesModal(String name, String img, String email, String background, String color) {
        this.name = name;
        this.img = img;
        this.email = email;
        this.background = background;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CategoriesModal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
