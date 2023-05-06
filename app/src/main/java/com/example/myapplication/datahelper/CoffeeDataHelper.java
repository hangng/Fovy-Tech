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
    private ArrayList<CoffeeCategory> mAryCoffeeCategory = new ArrayList<>();

    public ArrayList<Coffee> getCoffeeLst() {
        Log.i("TAG", "checking getCoffeeLst");
        return mAryCoffee;
    }

    public void setCoffeeLst(ArrayList<Coffee> aryCoffee) {
        this.mAryCoffee = aryCoffee;
    }

    public ArrayList<CoffeeCategory> getCoffeeCategory() {

        mAryCoffeeCategory.add(new CoffeeCategory("All", ""));
        mAryCoffeeCategory.add(new CoffeeCategory("Espresso", ""));
        mAryCoffeeCategory.add(new CoffeeCategory("Cappurchino", ""));
        mAryCoffeeCategory.add(new CoffeeCategory("Teh", ""));

        return mAryCoffeeCategory;
    }


    public ArrayList<Coffee> getCoffeeFav() {
        return mAryCoffeeFav;
    }

    public void setCoffeeFav(ArrayList<Coffee> aryCoffeeFav) {
        this.mAryCoffeeFav = aryCoffeeFav;
    }
}
