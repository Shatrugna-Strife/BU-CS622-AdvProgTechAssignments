package com.met622.entity;

import com.met622.Game;
import com.met622.constant.GameConstant;
import com.met622.constant.GameEvent;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.System.exit;

public class SwingGui implements Runnable {

    private JFrame f;
    public JButton submitButton;
    public TextField angleTextField;
    public TextField velocityTextField;
    public Label text;

    private PApplet game;

    private GameInstance gameInstance;

    public SwingGui(PApplet game, GameInstance gameInstance) {
        this.game = game;
        this.gameInstance = gameInstance;
        f = new JFrame("Gorilla Game");
        submitButton = new JButton("Click Here");
        angleTextField = new TextField("Enter Angle");
        velocityTextField = new TextField("Enter Velocity");
        text = new Label("Player1 enter angle(0<x<180) and velocity(0<x<10000)");
    }

    @Override
    public void run() {
        angleTextField.setBounds(10,40,100,30);
        velocityTextField.setBounds(110,40,100,30);
        text.setBounds(10,0,300,30);
        submitButton.setBounds(10,80,95,30);
        f.add(submitButton);
        f.add(angleTextField);
        f.add(velocityTextField);
        f.add(text);
        f.setSize(350,200);
        f.setLayout(null);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exit(0);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int angle = Integer.parseInt(angleTextField.getText());
                    int velocity = Integer.parseInt(velocityTextField.getText());
                    GameConstant.gameActions.get(GameEvent.BANANA_THROW).executeAction(game, SwingGui.this,gameInstance);
                    gameInstance.setAngle(angle);
                    gameInstance.setVelocity(velocity);
                }catch (NumberFormatException ex){
                    text.setText("Error: Enter only integer");
                }

            }
        });
    }
}
