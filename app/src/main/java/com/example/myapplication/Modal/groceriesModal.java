package com.example.myapplication.Modal;

public class groceriesModal     {
     private String image , name ,color, type ;


    public groceriesModal() {

    }

    public groceriesModal(String image, String name, String color , String type) {
        this.image = image;
        this.name = name;
        this.color = color;
        this.type = type;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
