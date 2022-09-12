package com.met622.model;

import com.met622.entity.GameInstance;
import com.met622.entity.SwingGui;
import processing.core.PApplet;

public interface EventAction {
    public void executeAction(PApplet game, SwingGui swingGui, GameInstance gameInstance);
}
