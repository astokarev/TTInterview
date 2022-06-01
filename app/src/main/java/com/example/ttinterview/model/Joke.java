package com.example.ttinterview.model;

public class Joke {
    public String value;

    public Joke(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
