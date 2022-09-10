package com.met622.entity;

import java.util.Scanner;

public class InputClassThread implements Runnable {

    @Override
    public void run() {
        while(true) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter velocity");
            Integer velocity = Integer.parseInt(myObj.nextLine());  // Read user input
            System.out.println("Enter angle");
            Integer angle = Integer.parseInt(myObj.nextLine());
            System.out.println("velocity is: " + velocity+ " "+angle);
        }
    }
}
