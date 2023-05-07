package com.example.myapplication.datahelper;

import android.util.Log;

import com.example.myapplication.model.Coffee;
import com.example.myapplication.model.CoffeeCategory;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoffeeDataHelper implements Serializable {
    private static final long serialVersionUID = 3578544398980689898L;

    private ArrayList<Coffee> mAryCoffee = new ArrayList<>();
    private ArrayList<Coffee> mAryCoffeeFav = new ArrayList<>();

    public ArrayList<Coffee> getCoffeeFav() {
        return mAryCoffeeFav;
    }

    public void setCoffeeFav(ArrayList<Coffee> aryCoffeeFav) {
        this.mAryCoffeeFav = aryCoffeeFav;
    }
}
