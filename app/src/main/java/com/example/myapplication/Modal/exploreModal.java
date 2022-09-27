package com.example.myapplication.Modal;

public class exploreModal {
    private  String name ,image, color, type;

    public exploreModal() {
    }

    public exploreModal(String name, String image, String color, String type) {
        this.name = name;
        this.image = image;
        this.color = color;
        this.type = type;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}
}
