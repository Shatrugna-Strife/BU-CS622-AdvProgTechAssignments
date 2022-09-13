package com.met622.entity;

import com.met622.constant.GameConstant;
import com.met622.model.BuildingModel;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Buildings class is used for generating buildings within the game randomly
 */
public class Buildings {
    private PApplet game;

    private List<BuildingModel> buildingModelList;

    /**
     *
     * @param game takes in PApplet class object which is used to instantiate the game loop
     */
    public Buildings(PApplet game){
        this.game = game;
        createBuildings();
    }

    /**
     * Called only once to randomly generate the buildings width and height.
     * Then place them on properly in the game scene.
     */
    private void createBuildings(){
        buildingModelList = new ArrayList<>();
        PVector startPos = new PVector(0,game.height);
        while(startPos.x<game.width){
            BuildingModel temp = new BuildingModel(game, startPos);
//            System.out.println(startPos.x+" "+ (startPos.y- temp.getHeight()) + " "+temp.getWidth()+" "+temp.getHeight());
//            float end_x = temp.getStart().x + temp.getWidth();
            startPos.x += temp.getWidth();
            buildingModelList.add(temp);
        }
    }

    /**
     * Render function needs to be called in the game draw method to paint the buildings objects.
     */
    public void renderAllBuildings(){
        for(int i = 0; i< buildingModelList.size(); i++){
            buildingModelList.get(i).render();
        }
    }

    public List<BuildingModel> getBuildingList(){
        return buildingModelList;
    }

    /**
     * collision check
     * @param pos position vector of the circle object
     * @param radius radius of the circular object
     * @return true if any of the buildings collided with the circular object
     */
    public boolean checkCircleCollision(PVector pos, int radius) {
        boolean tmp = false;
        for(int i = 0; i< buildingModelList.size(); i++){
            tmp |= GameConstant.physicsFormula.circleRect(pos.x, pos.y, radius,buildingModelList.get(i).getStart().x,buildingModelList.get(i).getStart().y-buildingModelList.get(i).getHeight(),buildingModelList.get(i).getWidth(), buildingModelList.get(i).getHeight());
        }
        return tmp;
    }
}
