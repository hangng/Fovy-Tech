package com.example.myapplication.model;

import java.io.Serializable;

public class CoffeeCategory implements Serializable {

    private String id;
    private String name;

    public CoffeeCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
