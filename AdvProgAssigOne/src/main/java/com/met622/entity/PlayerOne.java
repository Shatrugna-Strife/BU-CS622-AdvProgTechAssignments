package com.met622.entity;

import com.met622.model.BananaModel;
import com.met622.model.PlayerModel;
import processing.core.PApplet;

public class PlayerOne extends PlayerModel {

    private static PlayerOne instance;

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
