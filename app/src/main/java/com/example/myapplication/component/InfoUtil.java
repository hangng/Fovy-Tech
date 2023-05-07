package com.example.myapplication.component;

import com.example.myapplication.model.Coffee;
import com.example.myapplication.model.CoffeeData;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoUtil implements Serializable {
    private static final long serialVersionUID = -3665285664982467575L;

    private static InfoUtil mInfoUtil;
    private ArrayList<Coffee> aryCoffee;
    private ArrayList<Coffee> aryCoffeeFav;

    private CoffeeData mCoffeeData;
    private Coffee mCoffee;

    public static InfoUtil getInstance() {
        if (mInfoUtil == null) {
            mInfoUtil = new InfoUtil();
        }
        return mInfoUtil;
    }

    public static void setInfoUtil(InfoUtil infoUtil) {
        InfoUtil.mInfoUtil = infoUtil;
    }


    public ArrayList<Coffee> getCoffeeFav() {
        return aryCoffeeFav;
    }

    public void setCoffeeFav(ArrayList<Coffee> aryCoffee) {
        this.aryCoffeeFav = aryCoffee;
    }

    public ArrayList<Coffee> getCoffee() {
        return aryCoffee;
    }

    public void setCoffee(ArrayList<Coffee> aryCoffee) {
        this.aryCoffee = aryCoffee;
    }


    public CoffeeData getCoffeeData() {
        return mCoffeeData;
    }

    public void setCoffeeData(CoffeeData mCoffeeData) {
        this.mCoffeeData = mCoffeeData;
    }

    public Coffee getSelectCoffee() {
        return mCoffee;
    }

    public void setSelectCoffee(Coffee mCoffee) {
        this.mCoffee = mCoffee;
    }
}
