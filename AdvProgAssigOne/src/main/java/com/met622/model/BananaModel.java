package com.met622.model;

import processing.core.PApplet;
import processing.core.PVector;

public class BananaModel {
    private PApplet game;

    private static BananaModel instance;

    private PlayerModel player;

    private PVector pos;

    public static BananaModel getInstance(PApplet game, PlayerModel player){
        if(instance== null){
            instance = new BananaModel(game, player);
        }
        return instance;
    }

    private BananaModel(PApplet game, PlayerModel player){
        this.game = game;
        this.player = player;
        pos = new PVector(player.getPos().x, player.getPos().y - 10);
    }

    public void render(){
        game.push();
        game.fill(255,255,0);
        game.ellipse(pos.x, pos.y, 10,10);
        game.pop();
    }

    



}
