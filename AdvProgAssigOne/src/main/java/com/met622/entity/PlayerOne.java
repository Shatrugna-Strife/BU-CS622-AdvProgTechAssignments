package com.met622.entity;

import com.met622.model.BananaModel;
import com.met622.model.PlayerModel;
import processing.core.PApplet;

public class PlayerOne extends PlayerModel {

    private static PlayerOne instance; //static variable of the class instance

    /**
     * returns only a single instance, avoids creation of multiple instances. follows singleton pattern
     * @param game PApplet
     * @param x pos x axis
     * @param y pos y axis
     * @param bananaModel banana object instance
     * @return instance of the class
     */
    public static PlayerOne getInstance(PApplet game, float x, float y, BananaModel bananaModel){
        if(instance== null){
            instance = new PlayerOne(game, x,y, bananaModel);
        }
        return instance;
    }

    private BananaModel bananaModel;

    public PlayerOne(PApplet game, float x, float y, BananaModel bananaModel) {
        super(game, x, y);
        this.bananaModel = bananaModel;
    }

    public void render(){
        super.render(255, 100, 0);
    }

    @Override
    public int getDirection() {
        return 1;
    }
}
