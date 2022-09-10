package com.met622.model;

import processing.core.PApplet;
import processing.core.PVector;

public class PlayerModel {
    private PApplet game;

    protected String state;

    private PVector pos;

    public PlayerModel(PApplet game, float x, float y){
        this.game = game;
        pos = new PVector(x,y-(20/2));
    }

    public void render(float r, float g, float b){
        game.push();
        game.fill(r,g,b);
        game.ellipse(pos.x,pos.y, 20,20);
        game.pop();
    }

    public PVector getPos(){
        return pos;
    }
}
