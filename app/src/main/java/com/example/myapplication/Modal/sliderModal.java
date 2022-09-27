package com.example.myapplication.Modal;

import java.util.ArrayList;

public class sliderModal    {
    private String image;

    public sliderModal(ArrayList<sliderModal> sliderModalArrayList) {
    }

    public sliderModal(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
