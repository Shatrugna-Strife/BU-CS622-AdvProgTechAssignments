package com.met622.model;

import com.met622.constant.GameConstant;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class PlayerModel {
    private PApplet game;

    private PVector pos;

    public PlayerModel(PApplet game, float x, float y){
        this.game = game;
        pos = new PVector(x,y-GameConstant.PLAYER_RADIUS);
    }

    public void render(float r, float g, float b){
        game.push();
        game.fill(r,g,b);
        game.ellipse(pos.x,pos.y, 2*GameConstant.PLAYER_RADIUS,2*GameConstant.PLAYER_RADIUS);
        game.pop();
    }

    public PVector getPos(){
        return pos;
    }

    public abstract int getDirection();
    public boolean checkCircleCollide(float x, float y, int radius){
        return Math.abs((x - pos.x) * (x - pos.x) + (y - pos.y) * (y - pos.y)) < (radius + GameConstant.PLAYER_RADIUS) * (radius + GameConstant.PLAYER_RADIUS);
    }
}
