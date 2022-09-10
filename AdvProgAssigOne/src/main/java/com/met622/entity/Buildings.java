package com.met622.entity;

import com.met622.model.BuildingModel;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Buildings {
    private PApplet game;

    private List<BuildingModel> buildingModelList;

    public Buildings(PApplet game){
        this.game = game;
        createBuildings();
    }

    private void createBuildings(){
        buildingModelList = new ArrayList<>();
        PVector startPos = new PVector(0,game.height);
        System.out.println(startPos.x+" "+ startPos.y + " "+game.width);
        while(startPos.x<game.width){
            BuildingModel temp = new BuildingModel(game, startPos);
            System.out.println(startPos.x+" "+ (startPos.y- temp.getHeight()) + " "+temp.getWidth()+" "+temp.getHeight());
//            float end_x = temp.getStart().x + temp.getWidth();
            startPos.x += temp.getWidth();
            buildingModelList.add(temp);
        }
    }

    public void renderAllBuildings(){
        for(int i = 0; i< buildingModelList.size(); i++){
            buildingModelList.get(i).render();
        }
    }

    public List<BuildingModel> getBuildingList(){
        return buildingModelList;
    }

}
