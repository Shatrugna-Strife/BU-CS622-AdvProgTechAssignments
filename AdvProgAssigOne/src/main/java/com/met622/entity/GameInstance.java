package com.met622.entity;

import com.met622.constant.GameConstant;
import com.met622.constant.GameEvent;
import com.met622.model.BananaModel;
import com.met622.model.BuildingModel;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

/**
 * Game instance class which handles the game logic and draw order.
 */
public class GameInstance {

    PApplet game; // Game loop variable

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

    public SwingGui getSwingGui() {
        return swingGui;
    }

    private SwingGui swingGui;

    public GameInstance(PApplet game){
        this.game = game;
        buildings = new Buildings(game);
        PVector tmp = determinePlayerOnePosition();
        playerOne = PlayerOne.getInstance(game, tmp.x,tmp.y,null);
        tmp = determinePlayerTwoPosition();
        playerTwo = PlayerTwo.getInstance(game, tmp.x, tmp.y, null);
//        System.out.println(playerTwo.getDirection());
        // bananaModel = BananaModel.getInstance(game, playerOne);
        swingGui = new SwingGui(game,this);
        if(Math.round(Math.random())==0)
        GameConstant.gameActions.get(GameEvent.PLAYER_ONE_TURN).executeAction(game, swingGui,this);
        else GameConstant.gameActions.get(GameEvent.PLAYER_TWO_TURN).executeAction(game, swingGui,this);
        Thread t = new Thread(swingGui); // Thread instance to start a GUI on a different to avoid blocking the main loop thread
        t.start();
    }

    /**
     * method contains the order of drawing the objects, and collision check call
     */
    public void render(){
        buildings.renderAllBuildings();
        discardedBananaRender();
        playerOne.render();
        playerTwo.render();
        collisionCheck();
    }

    /**
     * Contains the collision detection method calls between the game ojects in the scene
     */
    private void collisionCheck(){
        if(bananaModel!=null && !bananaThrown)
            bananaModel.render();
        if(bananaThrown && bananaModel!=null){
            bananaModel.render(timeFromThrow(), angle, velocity);
            bananaThrown = !buildings.checkCircleCollision(bananaModel.getPos(), GameConstant.BANANA_RADIUS);
            if(!bananaThrown && bananaModel!=null){
                bananaDiscarded = bananaModel;
                bananaModel = null;//TODO - null fix
                projectileTimeTemp = 0;
                GameConstant.gameActions.get(GameEvent.BANANA_HIT_WALL).executeAction(game,swingGui,this);
                return;
            }
            if(bananaModel.getPlayer() == playerOne)
                bananaThrown = !playerTwo.checkCircleCollide(bananaModel.getPos().x,bananaModel.getPos().y, GameConstant.BANANA_RADIUS);
            else
                bananaThrown = !playerOne.checkCircleCollide(bananaModel.getPos().x,bananaModel.getPos().y, GameConstant.BANANA_RADIUS);
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

    /**
     * draws the previous banana
     */
    private void discardedBananaRender(){
        if(bananaDiscarded!=null)
        bananaDiscarded.render();
    }

    /**
     * Stores and increment the time from the moment player throws the banana
     * @return time until now
     */
    private double timeFromThrow(){
        if(bananaThrown) {
            projectileTimeTemp += (double) 1 / (double) game.frameRate;
        }else {
            projectileTimeTemp = 0;
        }
        return projectileTimeTemp;
    }

    /**
     * determines randomly the player position over randomly generated buildings
     * @return vector position of player one
     */
    private PVector determinePlayerOnePosition(){
        PVector playerOneLocation = new PVector();
        playerOneLocation.x = GameConstant.RAND.nextInt(GameConstant.PLAYER_MAX_X_LOCATION-GameConstant.PLAYER_MIN_X_LOCATION+1) + GameConstant.PLAYER_MIN_X_LOCATION;
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
        int playerMinLocation = game.width-GameConstant.PLAYER_MAX_X_LOCATION;
        int playerMaxLocation = game.width-GameConstant.PLAYER_MIN_X_LOCATION;
        PVector playerTwoLocation = new PVector();
        playerTwoLocation.x = GameConstant.RAND.nextInt(playerMaxLocation-playerMinLocation+1) + playerMinLocation;
        int tmp = 0;
        for(BuildingModel b : buildings.getBuildingList()){
            if(playerTwoLocation.x > tmp && playerTwoLocation.x <= tmp + b.getWidth()){
                playerTwoLocation.y = game.height - b.getHeight();
                playerTwoLocation.x = b.getStart().x + (int)(b.getWidth()/2);
                break;
            }else{
                tmp += b.getWidth();
            }
        }
        return playerTwoLocation;
    }


}
