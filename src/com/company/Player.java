package com.company;

public class Player {
    int id;
    String name;
    int currentIndex;
    boolean skipRound;
    boolean joker;


    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        currentIndex = 0;
        skipRound = false;
        joker = false;
    }
    public void movePlayer(int steps){
        this.currentIndex += steps;
    }

}