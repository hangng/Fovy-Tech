package com.example.myapplication.model;

public class CoffeeFavorite {
    private String name;
    private boolean mbFavorite;


    public CoffeeFavorite(String title, boolean favorite) {
        this.name = title;
        this.mbFavorite = favorite;
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return mbFavorite;
    }

}
