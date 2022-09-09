package com.met622.model;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class Building {
    private PApplet game;

    private int height;

    private int width;

    private PVector start;

    public Building(PApplet game){
        this.game = game;
        this.width = new Random().nextInt(70 - 20 + 1) + 20;
        this.height = new Random().nextInt(40 - 10 + 1) + 10;
        start = new PVector(0,game.height);
    }

    public void render(){
        game.push();
        game.fill(0);
        game.rect(start.x , start.y-height, start.x + width, start.y);
        game.pop();
    }


}
