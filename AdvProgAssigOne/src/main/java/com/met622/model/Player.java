package com.met622.model;

import processing.core.PApplet;

public class Player {
    private PApplet game;

    public Player(PApplet game, float x, float y){
        this.game = game;
    }

    public void render(){
        game.push();
        game.fill(255,100,0);
        game.ellipse(game.width - game.width + 100, game.height - 200, 20,20);
        game.pop();
    }
}
