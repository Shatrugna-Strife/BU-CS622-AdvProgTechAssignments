package com.met622.model;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class BuildingModel {
    private PApplet game;

    private int height;

    private int width;

    private PVector start;

    public BuildingModel(PApplet game){
        this.game = game;
        this.width = new Random().nextInt(50 - 20 + 1) + 20;
        this.height = new Random().nextInt(150 - 10 + 1) + 10;
        this.start = new PVector(0,game.height);
    }

    public BuildingModel(PApplet game, PVector start){
        this.game = game;
        this.width = new Random().nextInt(50 - 20 + 1) + 20;
        this.height = new Random().nextInt(150 - 50 + 1) + 50;
        this.start = new PVector(start.x,start.y);
    }

    public void render(){
        game.push();
        game.fill(new Random().nextInt());
        game.stroke(100,100,0);
        game.strokeWeight(3);
        game.rect(start.x , start.y-height, width, height);
        game.pop();
    }

    public PVector getStart() {
        return start;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
