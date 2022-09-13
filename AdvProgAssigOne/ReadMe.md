## Requirements

Tested on - `OpenJDK JDK 18.0.2.1`, `Windows 10 OS`

Link to download [https://jdk.java.net/18/](https://jdk.java.net/18/)

## Run

Command to start the program on Windows machine
```
gradlew.bat build   //Build the project
java -cp .\libs\core.jar;.\build\libs\AdvProgAssigOne-1.0.jar com.met622.Game   //Run the application
```

## Description
A monkey banana throw game with displayable playing screen which takes mouse click as input and swing gui to enter input 
manually.

### Details - Open source library

The java project uses an open source java visual library to provide drawing api. [Processing 4.0.1 Download Page](https://github.com/processing/processing4/releases/tag/processing-1286-4.0.1). [Processing Home Page](https://processing.org/)

A core.jar file, in processing-4.0.1/core/library, is placed in the libs folder of the project.

### Features - Building

The game has a random building generation at the start of the game. It varies in size everytime you start the game and 
the player position is randomly placed on the building without coinciding with the buildings.

### Features - Collision Detection

The game offers collision detection between banana object and all the other objects on the scene. Since the game runs on 
a loop, it checks for the collision every single time.

### Packages - Entity

Classes in this package are spawned in the game scene.

### Game.java

It is the entry point of the game, it extends PApplet class which is a inherent property of processing application library 
