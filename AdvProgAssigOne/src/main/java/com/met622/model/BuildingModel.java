package com.met622.model;

import com.met622.constant.GameConstant;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

/**
 * Kinda Building POJO class, i guess
 */
public class BuildingModel {
    private PApplet game;

    private int height;

    private int width;

    private PVector start;

    public BuildingModel(PApplet game){
        this.game = game;
        this.width = GameConstant.RAND.nextInt(GameConstant.BUILDING_MAX_WIDTH - GameConstant.BUILDING_MIN_WIDTH + 1) +GameConstant.BUILDING_MIN_WIDTH;
        this.height = GameConstant.RAND.nextInt(GameConstant.BUILDING_MAX_HEIGHT -GameConstant.BUILDING_MIN_HEIGHT + 1) + GameConstant.BUILDING_MIN_HEIGHT;
        this.start = new PVector(0,game.height);
    }

    public BuildingModel(PApplet game, PVector start){
        this.game = game;
        this.width = GameConstant.RAND.nextInt(GameConstant.BUILDING_MAX_WIDTH - GameConstant.BUILDING_MIN_WIDTH + 1) +GameConstant.BUILDING_MIN_WIDTH;
        this.height = GameConstant.RAND.nextInt(GameConstant.BUILDING_MAX_HEIGHT -GameConstant.BUILDING_MIN_HEIGHT + 1) + GameConstant.BUILDING_MIN_HEIGHT;
        this.start = new PVector(start.x,start.y);
    }

    public void render(){
        game.push();
        game.fill(GameConstant.RAND.nextInt());
        game.stroke(100,100,0);
        game.strokeWeight(3);
        game.rect(start.x , start.y-height, width, height);
        game.pop();
    }

    public PVector getStart() {
        return start;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
