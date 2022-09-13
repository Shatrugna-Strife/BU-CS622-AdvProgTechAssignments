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

    /**
     * it gets called only once while the loading the instance of the class to the game loop.
     */
    @Override
    public void settings(){
        size(GameConstant.SCREEN_WIDTH, GameConstant.SCREEN_HEIGHT);
//        this.frameRate(30); // it displays a weird null pointer exception
        gameInstance = new GameInstance(this);
    }

    /**
     * it gets called probably around 60 times per sec.
     * the drawing and collision detection takes place over here
     */
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

    /**
     * handles the mouse click event.
     * determines the velocity and angle to throw the banana.
     */
    public void mousePressed(){
        if(gameInstance.getBananaModel()!=null&&!gameInstance.isBananaThrown()){
            PVector tmpBananaPos = gameInstance.getBananaModel().getPos();
            int angle = (int)(Math.atan(Math.abs(mouseY-tmpBananaPos.y)/Math.abs(mouseX - tmpBananaPos.x))/0.0174533d);
            int velocity = (int) Math.sqrt(Math.pow(mouseX-tmpBananaPos.x,2)+Math.pow(mouseY-tmpBananaPos.y,2));
            gameInstance.setVelocity(velocity);
            gameInstance.setAngle(angle);
            GameConstant.gameActions.get(GameEvent.BANANA_THROW).executeAction(this, gameInstance.getSwingGui(),gameInstance);
        }
    }

    public static void main(String[] args){
        String[] processingArgs = {"Game"};
        Game game = new Game();
        PApplet.runSketch(processingArgs, game); // loads the game loop
    }
}