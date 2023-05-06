package com.example.myapplication.model;

public class CoffeeCategory {

    private String msTitle, msImg;



    public CoffeeCategory(String title,String img) {
        this.msTitle = title;
        this.msImg = img;
    }

    public String getTitle() {
        return msTitle;
    }

    public String getImg() {
        return msImg;
    }
}
