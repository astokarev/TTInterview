package com.example.ttinterview.model;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categories;
    public Categories(){
        categories = new ArrayList<>();
    }
    public void add(String value){
        categories.add(value);
    }
    public String getCategory(int i){
       return categories.get(i);
    }
    public int getLength(){
        return categories.toArray().length;
    }
}
