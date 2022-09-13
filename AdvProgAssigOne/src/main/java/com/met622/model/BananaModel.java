package com.met622.model;

import com.met622.constant.GameConstant;
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
        game.ellipse(pos.x, pos.y, GameConstant.BANANA_RADIUS*2,GameConstant.BANANA_RADIUS*2);
        game.pop();
    }

    /**
     * overloaded render method to pass the below parameters
     * @param timeAfterThrow
     * @param angle
     * @param velocity
     */
    public void render(double timeAfterThrow,int angle, int velocity){
        game.push();
        game.fill(255,255,0);
        projectileUpdate(player, timeAfterThrow,angle, velocity);
        game.ellipse(pos.x, pos.y, GameConstant.BANANA_RADIUS*2,GameConstant.BANANA_RADIUS*2);
        game.pop();
    }

    /**
     * method to update the banana position in the projectile motion trajectory using the below parameters
     * @param playerModel
     * @param time
     * @param angle
     * @param velocity
     */
    public void projectileUpdate(PlayerModel playerModel, double time, int angle, int velocity){
        projectileMotionTempVector.x = (int)(velocity * Math.cos(angle*0.0174533d) * time);
        projectileMotionTempVector.y = (int)(velocity * Math.sin(angle*0.0174533d) * time - 0.5 * GameConstant.GRAVITY * (Math.pow(time,2)));
        pos.x = playerModel.getDirection() * projectileMotionTempVector.x + initialPos.x;
//        System.out.println(playerModel.getDirection() +" "+pos.x);
        pos.y = initialPos.y - projectileMotionTempVector.y;
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
