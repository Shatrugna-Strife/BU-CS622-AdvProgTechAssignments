package com.met622.entity;

import com.met622.model.BananaModel;
import com.met622.model.BuildingModel;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class GameInstance {

    PApplet game;
    private BananaModel bananaModel;
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Buildings buildings;

    public GameInstance(PApplet game){
        this.game = game;
        buildings = new Buildings(game);
        PVector tmp = determinePlayerOnePosition();
        System.out.println(tmp.x+" "+tmp.y+" fadf");
        playerOne = PlayerOne.getInstance(game, tmp.x,tmp.y,null);
        tmp = determinePlayerTwoPosition();
        playerTwo = PlayerTwo.getInstance(game, tmp.x, tmp.y, null);
        bananaModel = BananaModel.getInstance(game, playerOne);
        Thread t = new Thread(new InputClassThread());
        t.start();
    }

    public void render(){
        buildings.renderAllBuildings();
        playerOne.render();
        playerTwo.render();
        bananaModel.render();
    }

    private PVector determinePlayerOnePosition(){
        int playerMinLocation = 10;
        int playerMaxLocation = 100;
        PVector playerOneLocation = new PVector();
        playerOneLocation.x = new Random().nextInt(100-10+1) + 10;
        int tmp = 0;
        for(BuildingModel b : buildings.getBuildingList()){
            if(playerOneLocation.x > tmp && playerOneLocation.x <= tmp + b.getWidth()){
                playerOneLocation.y = game.height - b.getHeight();
                playerOneLocation.x = b.getStart().x + (int)(b.getWidth()/2);
                break;
            }else{
                tmp += b.getWidth();
            }
        }
        return playerOneLocation;
    }

    private PVector determinePlayerTwoPosition(){
        int playerMinLocation = game.width-100;
        int playerMaxLocation = game.width-10;
        PVector playerOneLocation = new PVector();
        playerOneLocation.x = new Random().nextInt(playerMaxLocation-playerMinLocation+1) + playerMinLocation;
        int tmp = 0;
        for(BuildingModel b : buildings.getBuildingList()){
            if(playerOneLocation.x > tmp && playerOneLocation.x <= tmp + b.getWidth()){
                playerOneLocation.y = game.height - b.getHeight();
                playerOneLocation.x = b.getStart().x + (int)(b.getWidth()/2);
                break;
            }else{
                tmp += b.getWidth();
            }
        }
        return playerOneLocation;
    }


}
