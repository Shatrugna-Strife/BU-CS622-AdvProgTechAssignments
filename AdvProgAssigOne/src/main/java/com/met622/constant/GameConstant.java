package com.met622.constant;

import com.met622.entity.GameInstance;
import com.met622.entity.SwingGui;
import com.met622.model.BananaModel;
import com.met622.model.GameEventAction;
import com.met622.util.PhysicsFormula;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Interface to store the constants, by default interface variables are taken as
 * public static final.
 * Ex: int height ---> public static final int height;
 */
public interface GameConstant {

    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 600;
    int BANANA_RADIUS = 5;
    int PLAYER_MAX_X_LOCATION = 100;
    int PLAYER_MIN_X_LOCATION = 10;
    int GRAVITY = 100;
    int BUILDING_MAX_WIDTH = 50;
    int BUILDING_MIN_WIDTH = 20;
    int BUILDING_MAX_HEIGHT = 150;
    int BUILDING_MIN_HEIGHT = 50;
    int PLAYER_RADIUS = 10;

    Random RAND = new Random();
    PhysicsFormula physicsFormula = new PhysicsFormula();

    /**
     * Map to store the functions which needs to be executed at certain Game events.
     */
    Map<GameEvent, GameEventAction> gameActions = new HashMap<>(){{
        put(GameEvent.PLAYER_ONE_TURN, new GameEventAction() {
            @Override
            public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance) {
                swingGui.text.setText("Player1 enter angle(0<x<180) and velocity(0<x<10000)");
                gameInstance.setBananaThrown(false);
                gameInstance.setBananaModel(new BananaModel(game, gameInstance.getPlayerOne()));
                swingGui.submitButton.setEnabled(true);
            }
        });
        put(GameEvent.PLAYER_TWO_TURN, new GameEventAction() {
            @Override
            public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance) {
                swingGui.text.setText("Player2 enter angle(0<x<180) and velocity(0<x<10000)");
                gameInstance.setBananaThrown(false);
                gameInstance.setBananaModel(new BananaModel(game, gameInstance.getPlayerTwo()));
                swingGui.submitButton.setEnabled(true);
            }
        });
        put(GameEvent.BANANA_THROW, new GameEventAction() {
            @Override
            public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance) {
                swingGui.text.setText("banana is flying");
                gameInstance.setBananaThrown(true);
                swingGui.submitButton.setEnabled(false);
            }
        });
        put(GameEvent.BANANA_HIT_WALL, new GameEventAction() {
            @Override
            public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance) {
//                swingGui.text.setText("banana is flying");
                gameInstance.setBananaThrown(false);
                if(gameInstance.getBananaDiscarded().getPlayer() == gameInstance.getPlayerOne()){
                    gameInstance.setBananaModel(new BananaModel(game, gameInstance.getPlayerTwo()));
                    swingGui.text.setText("Failed to hit. Player2 turn");
                }
                if(gameInstance.getBananaDiscarded().getPlayer() == gameInstance.getPlayerTwo()){
                    gameInstance.setBananaModel(new BananaModel(game, gameInstance.getPlayerOne()));
                    swingGui.text.setText("Failed to hit. Player1 turn");
                }
                swingGui.submitButton.setEnabled(true);
            }
        });
        put(GameEvent.BANANA_HIT_PLAYER, new GameEventAction() {
            @Override
            public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance) {
                game.noLoop();
                if(gameInstance.getBananaDiscarded().getPlayer() == gameInstance.getPlayerOne())
                    swingGui.text.setText("Player 1 won the game");
                if(gameInstance.getBananaDiscarded().getPlayer() == gameInstance.getPlayerTwo())
                    swingGui.text.setText("Player 2 won the game");
            }
        });
    }};
}

