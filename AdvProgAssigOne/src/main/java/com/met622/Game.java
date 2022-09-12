package com.met622;

import com.met622.constant.GameConstant;
import com.met622.constant.GameEvent;
import com.met622.entity.Buildings;
import com.met622.entity.GameInstance;
import com.met622.entity.SwingGui;
import com.met622.model.PlayerModel;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

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
        dashedLine( gameInstance.getBananaModel().getPos().x,  gameInstance.getBananaModel().getPos().y,mouseX,mouseY);
//        ellipse(mouseX, mouseY, 100, 50);
        gameInstance.render();
//        System.out.println(frameRate);
    }

    private void dashedLine(float x1, float y1, float x2, float y2){
        float dX = (x2-x1)/40;
        float dY = (y2-y1)/40;
        this.push();
        this.strokeWeight(1);
        for(int i =40;i>0;i-=2){
            this.line(dX*(i-1)+x1,dY*(i-1)+y1,dX*i + x1, dY*i+y1);
        }
        this.pop();
    }

    public void mousePressed(){
        if(!gameInstance.isBananaThrown()){
            PVector tmpPlayerPos = gameInstance.getBananaModel().getPlayer().getPos();
            PVector tmpBananaPos = gameInstance.getBananaModel().getPos();
            int angle = (int)(Math.atan((Math.abs(mouseY-tmpBananaPos.y))/(Math.abs(mouseX - tmpBananaPos.x)))/0.0174533d);
            int velocity = (int) Math.sqrt(Math.pow(mouseX-tmpBananaPos.x,2)+Math.pow(mouseY-tmpBananaPos.y,2));
            gameInstance.setVelocity(velocity);
            gameInstance.setAngle(angle);
            GameConstant.gameActions.get(GameEvent.BANANA_THROW).executeAction(this, gameInstance.getSwingGui(),gameInstance);
        }
    }

    public static void main(String[] args){
        String[] processingArgs = {"Game"};
        Game game = new Game();
        PApplet.runSketch(processingArgs, game);
    }
}