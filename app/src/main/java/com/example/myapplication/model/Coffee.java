package com.example.myapplication.model;

import java.io.Serializable;

public class Coffee implements Serializable {
    private static final long serialVersionUID = -4763567154698548274L;
    private String name, time, description, ingredients, instructions, category_id, serve, url_link;
    private boolean mbFavorite;

    public Coffee(String title, String time, String description, String ingredients, String instructions, boolean favorite, String imgUrl, String serve, String category_id) {
        this.name = title;
        this.time = time;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.mbFavorite = favorite;
        this.url_link = imgUrl;
        this.serve = serve;
        this.category_id = serve;
    }


    public String getCatId() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public boolean isFavorite() {
        return mbFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mbFavorite = favorite;
    }

    public String getServe() {
        return serve;
    }

    public String getUrl() {
        return url_link;
    }
}
