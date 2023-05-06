package com.example.myapplication.model;

public class Coffee {

    public static int CAT_HEADER = 0;
    public static int CAT_BODY = 1;


    private String msTitle, msTime, msDescription, msIngredients, msInstructions;
    private boolean mbFavorite;
    private int miSectionId;


    public Coffee(String title, String time, String description, String ingredients, String instructions, boolean favorite) {
        this.msTitle = title;
        this.msTime = time;
        this.msDescription = description;
        this.msIngredients = ingredients;
        this.msInstructions = instructions;
        this.mbFavorite = favorite;
    }


    public int getSectionId() {
        return miSectionId;
    }

    public void setSectionId(int sectionId) {
        this.miSectionId = sectionId;
    }

    public String getTitle() {
        return msTitle;
    }

    public void setTitle(String title) {
        this.msTitle = title;
    }

    public String getTime() {
        return msTime;
    }

    public void setTime(String time) {
        this.msTime = time;
    }

    public String getDescription() {
        return msDescription;
    }

    public void setDescription(String description) {
        this.msDescription = description;
    }

    public String getIngredients() {
        return msIngredients;
    }

    public void setIngredients(String ingredients) {
        this.msIngredients = ingredients;
    }

    public String getInstructions() {
        return msInstructions;
    }

    public void setInstructions(String instructions) {
        this.msInstructions = instructions;
    }

    public boolean isFavorite() {
        return mbFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mbFavorite = favorite;
    }
}
