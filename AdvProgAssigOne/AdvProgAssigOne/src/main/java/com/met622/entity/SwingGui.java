package com.met622.entity;

import com.met622.constant.GameConstant;
import com.met622.constant.GameEvent;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.System.exit;

/**
 *
 */
public class SwingGui implements Runnable {

    private JFrame jFrame;
    public JButton submitButton;
    public TextField angleTextField;
    public TextField velocityTextField;
    public Label text;

    private PApplet game;

    private GameInstance gameInstance;

    public SwingGui(PApplet game, GameInstance gameInstance) {
        this.game = game;
        this.gameInstance = gameInstance;
        jFrame = new JFrame("Gorilla Game");
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
        jFrame.add(submitButton);
        jFrame.add(angleTextField);
        jFrame.add(velocityTextField);
        jFrame.add(text);
        jFrame.setSize(350,200);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                exit(0);
            }
        });

        angleTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                try{
                    Integer.parseInt(angleTextField.getText());
                }catch (NumberFormatException ex){
                    angleTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Integer.parseInt(angleTextField.getText());
                }catch (NumberFormatException ex){
                    angleTextField.setText("Enter Angle");
                }
            }
        });
        velocityTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                try{
                    Integer.parseInt(velocityTextField.getText());
                }catch (NumberFormatException ex){
                    velocityTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    Integer.parseInt(velocityTextField.getText());
                }catch (NumberFormatException ex){
                    velocityTextField.setText("Enter Velocity");
                }
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
