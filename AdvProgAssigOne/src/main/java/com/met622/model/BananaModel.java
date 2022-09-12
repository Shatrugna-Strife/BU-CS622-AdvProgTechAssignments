package com.met622.model;

import processing.core.PApplet;
import processing.core.PVector;

public class BananaModel {
    private PApplet game;

    public PlayerModel getPlayer() {
        return player;
    }

    private PlayerModel player;

    private PVector pos;

    private PVector projectileMotionTempVector = new PVector();

    private PVector initialPos;

    public BananaModel(PApplet game, PlayerModel player){
        this.game = game;
        this.player = player;
        pos = new PVector(player.getPos().x, player.getPos().y - 10);
        initialPos = new PVector(pos.x,pos.y);
    }

    public void render(){
        game.push();
        game.fill(255,255,0);
        game.ellipse(pos.x, pos.y, 10,10);
        game.pop();
    }

    public void render(double timeAfterThrow,int angle, int velocity){
        game.push();
        game.fill(255,255,0);
        projectileUpdate(player, timeAfterThrow,angle, velocity);
        game.ellipse(pos.x, pos.y, 10,10);
        game.pop();
    }

    public void projectileUpdate(PlayerModel playerModel, double time, int angle, int velocity){
        projectileMotionFormula(time, angle, velocity);
        pos.x = playerModel.getDirection() * projectileMotionTempVector.x + initialPos.x;
        System.out.println(playerModel.getDirection() +" "+pos.x);
        pos.y = initialPos.y - projectileMotionTempVector.y;
    }

    public void projectileMotionFormula(double time, int angle, int initialVelocity){
        projectileMotionTempVector.x = (int)(initialVelocity * Math.cos(angle*0.0174533d) * time);
        projectileMotionTempVector.y = (int)(initialVelocity * Math.sin(angle*0.0174533d) * time - 0.5 * 100 * (Math.pow(time,2)));
    }

    public PVector getPos(){
        return pos;
    }

    public boolean collisionWithVerticalLineLeft(int x){
        return pos.x - 5 < x;
    }

    public boolean collisionWithVerticalLineRight(int x){
        return pos.x + 5 > x;
    }

}
