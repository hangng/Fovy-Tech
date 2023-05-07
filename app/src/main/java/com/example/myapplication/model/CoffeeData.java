package com.example.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CoffeeData implements Serializable {
    private static final long serialVersionUID = 3601705467107436562L;
    private ArrayList<CoffeeCategory> coffee_category;
    private ArrayList<Coffee> coffee_data;

    public ArrayList<CoffeeCategory> getCoffeeCategory() {
        return coffee_category;
    }

    public ArrayList<Coffee> getCoffeeData() {
        return coffee_data;
    }
}
