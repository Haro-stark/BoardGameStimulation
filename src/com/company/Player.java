package com.company;


import java.util.ArrayList;
import java.util.List;

public class Player {
    int id;
    String name;
    int currentIndex;
    ArrayList<Integer> deActivatedSpecialIndex;
    boolean skipRound;
    boolean joker;


    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        currentIndex = 0;
        deActivatedSpecialIndex = new ArrayList<Integer>();
        skipRound = false;
        joker = false;
    }
    public void movePlayer(int steps){
        this.currentIndex += steps;
    }

}