package com.met622.entity;

import com.met622.constant.GameConstant;
import com.met622.constant.GameEvent;
import com.met622.model.BananaModel;
import com.met622.model.BuildingModel;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInstance {

    PApplet game;

    public BananaModel getBananaModel() {
        return bananaModel;
    }

    public void setBananaModel(BananaModel bananaModel) {
        this.bananaModel = bananaModel;
    }

    private BananaModel bananaModel;

    public PlayerOne getPlayerOne() {
        return playerOne;
    }

    private PlayerOne playerOne;

    public PlayerTwo getPlayerTwo() {
        return playerTwo;
    }

    private PlayerTwo playerTwo;
    private Buildings buildings;

    public BananaModel getBananaDiscarded() {
        return bananaDiscarded;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    private int angle;

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    private int velocity;

    private BananaModel bananaDiscarded = null;

    public boolean isBananaThrown() {
        return bananaThrown;
    }

    public void setBananaThrown(boolean bananaThrown) {
        this.bananaThrown = bananaThrown;
    }

    private boolean bananaThrown = true; //TODO - false default
    private double projectileTimeTemp = 0;
    private SwingGui swingGui;

    public GameInstance(PApplet game){
        this.game = game;
        buildings = new Buildings(game);
        PVector tmp = determinePlayerOnePosition();
        System.out.println(tmp.x+" "+tmp.y+" fadf");
        playerOne = PlayerOne.getInstance(game, tmp.x,tmp.y,null);
        tmp = determinePlayerTwoPosition();
        playerTwo = PlayerTwo.getInstance(game, tmp.x, tmp.y, null);
        System.out.println(playerTwo.getDirection());
        // bananaModel = BananaModel.getInstance(game, playerOne);
        swingGui = new SwingGui(game,this);
        if(Math.round(Math.random())==0)
        GameConstant.gameActions.get(GameEvent.PLAYER_ONE_TURN).executeAction(game, swingGui,this);
        else GameConstant.gameActions.get(GameEvent.PLAYER_TWO_TURN).executeAction(game, swingGui,this);
        Thread t = new Thread(swingGui);
        t.start();
    }

    public void render(){
        buildings.renderAllBuildings();
        discardedBananaRender();
        playerOne.render();
        playerTwo.render();
        if(bananaModel!=null && !bananaThrown)
        bananaModel.render();
        if(bananaThrown && bananaModel!=null){
            bananaModel.render(timeFromThrow(), angle, velocity);
            bananaThrown = !buildings.circleCollision(bananaModel.getPos());
            if(!bananaThrown && bananaModel!=null){
                bananaDiscarded = bananaModel;
                bananaModel = null;//TODO - null fix
                projectileTimeTemp = 0;
                GameConstant.gameActions.get(GameEvent.BANANA_HIT_WALL).executeAction(game,swingGui,this);
                return;
            }
            if(bananaModel.getPlayer() == playerOne)
                bananaThrown = !playerTwo.checkCircleCollide(bananaModel.getPos().x,bananaModel.getPos().y,5);
            else
                bananaThrown = !playerOne.checkCircleCollide(bananaModel.getPos().x,bananaModel.getPos().y,5);
            if(!bananaThrown && bananaModel!=null){
                bananaDiscarded = bananaModel;
                bananaModel = null;//TODO - null fix
                projectileTimeTemp = 0;
                GameConstant.gameActions.get(GameEvent.BANANA_HIT_PLAYER).executeAction(game,swingGui,this);
                return;
            }
            bananaThrown = !(bananaModel.collisionWithVerticalLineLeft(0)||bananaModel.collisionWithVerticalLineRight(game.width));
            if(!bananaThrown && bananaModel!=null){
                bananaDiscarded = bananaModel;
                bananaModel = null;//TODO - null fix
                projectileTimeTemp = 0;
                GameConstant.gameActions.get(GameEvent.BANANA_HIT_WALL).executeAction(game,swingGui,this);
                return;
            }
        }
    }

    private void discardedBananaRender(){
        if(bananaDiscarded!=null)
        bananaDiscarded.render();
    }

    private double timeFromThrow(){
        if(bananaThrown) {
            projectileTimeTemp += (double) 1 / (double) game.frameRate;
        }else {
            projectileTimeTemp = 0;
        }
        return projectileTimeTemp;
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
