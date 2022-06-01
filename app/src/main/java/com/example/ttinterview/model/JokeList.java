package com.example.ttinterview.model;

import java.util.ArrayList;

public class JokeList {
    //public String[] titles;
    private ArrayList<Joke> jokes;

    public JokeList() {
        jokes = new ArrayList<>();
    }

    public void add(Joke value) {
        jokes.add(value);
        //updateTitles();
    }

    /*public void updateTitles() {
        titles = new String[jokes.toArray().length];
        for (int i = 0; i < jokes.toArray().length; i++) {
            titles[i] = jokes.get(i).value;
        }
    }*/

    public String getJoke(int i) {
        return jokes.get(i).toString();
    }
    public int getLength(){
        return jokes.toArray().length;
    }
}
