package com.example.myapplication.component;

import com.example.myapplication.model.Coffee;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoUtil implements Serializable {


    private static InfoUtil mInfoUtil;
    private ArrayList<Coffee> aryCoffee;

    public static InfoUtil getInstance() {
        if (mInfoUtil == null) {
            mInfoUtil = new InfoUtil();
        }
        return mInfoUtil;
    }

    public static void setInfoUtil(InfoUtil infoUtil) {
        InfoUtil.mInfoUtil = infoUtil;
    }


    public ArrayList<Coffee> getCoffee() {
        return aryCoffee;
    }

    public void setCoffee(ArrayList<Coffee> aryCoffee) {
        this.aryCoffee = aryCoffee;
    }
}
