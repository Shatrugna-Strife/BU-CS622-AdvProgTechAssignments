package com.met622;

import com.met622.constant.GameConstant;
import com.met622.entity.Buildings;
import com.met622.entity.GameInstance;
import com.met622.model.PlayerModel;
import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet{

    private GameInstance gameInstance;
    @Override
    public void settings(){
        size(GameConstant.SCREEN_WIDTH, GameConstant.SCREEN_HEIGHT);
//        this.frameRate(30);
        gameInstance = new GameInstance(this);
        loadImage("");
    }

    @Override
    public void draw(){
        background(255);
//        ellipse(mouseX, mouseY, 100, 50);
        gameInstance.render();
//        System.out.println(frameRate);
    }

    public void mousePressed(){

    }

    public static void main(String[] args){
        String[] processingArgs = {"Game"};
        Game game = new Game();
        PApplet.runSketch(processingArgs, game);
    }
}