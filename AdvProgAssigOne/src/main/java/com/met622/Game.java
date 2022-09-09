package com.met622;

import com.met622.constant.GameConstant;
import com.met622.model.Building;
import com.met622.model.Player;
import processing.core.PApplet;

public class Game extends PApplet{

    Player p ;
    Building b;

    public void settings(){
        size(GameConstant.SCREEN_WIDTH, GameConstant.SCREEN_HEIGHT);
        p = new Player(this, 2,2);
        b = new Building(this);
    }

    public void draw(){
        background(255);
//        ellipse(mouseX, mouseY, 100, 50);
        p.render();
        b.render();
    }

    public void mousePressed(){

    }

    public static void main(String[] args){
        String[] processingArgs = {"Game"};
        Game game = new Game();

        PApplet.runSketch(processingArgs, game);
    }
}