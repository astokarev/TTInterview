package com.example.ttinterview.model;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categories;

    public Categories() {
        categories = new ArrayList<>();
    }

    public void add(String value) {
        categories.add(value);
    }

    public String getCategory(int i) {
        return categories.get(i);
    }

    public int getLength() {
        return categories.toArray().length;
    }

    public String[] categoriesArray() {

        String[] result = new String[categories.toArray().length];
        for (int i = 0; i < categories.toArray().length; i++) {
            result[i] = categories.get(i);
        }
        return result;
    }

    public String[] offlineCategories() {
        String[] result = new String[]{"animal", "career", "celebrity", "dev",
                "explicit", "fashion", "food", "history", "money",
                "movie", "music", "political", "religion", "science",
                "sport", "travel"};
        return result;
    }
}
