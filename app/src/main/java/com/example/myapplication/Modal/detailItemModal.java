package com.example.myapplication.Modal;

public class detailItemModal {
    private  String image, name , type,description;
    private double  price;

    public detailItemModal() {
    }

    public detailItemModal(String image, String name, String type, double price,String description) {
        this.image = image;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
