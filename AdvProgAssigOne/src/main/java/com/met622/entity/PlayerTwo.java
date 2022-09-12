package com.met622.entity;

import com.met622.model.BananaModel;
import com.met622.model.PlayerModel;
import processing.core.PApplet;

public class PlayerTwo extends PlayerModel {

    private static PlayerTwo instance;

    public static PlayerTwo getInstance(PApplet game, float x, float y, BananaModel bananaModel){
        if(instance== null){
            instance = new PlayerTwo(game, x,y, bananaModel);
        }
        return instance;
    }

    private BananaModel bananaModel;

    public PlayerTwo(PApplet game, float x, float y, BananaModel bananaModel) {
        super(game, x, y);
        this.bananaModel = bananaModel;
    }

    public void render(){
        super.render(100, 255, 0);
    }

    @Override
    public int getDirection() {
        return -1;
    }
}
