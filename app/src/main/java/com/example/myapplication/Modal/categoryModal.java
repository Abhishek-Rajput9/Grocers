package com.example.myapplication.Modal;

public class categoryModal {
 private String image , name ,description;
 private double price;

    public categoryModal() {
    }

    public categoryModal(String image, String name, double price,String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
