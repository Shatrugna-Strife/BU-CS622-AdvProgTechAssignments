## Requirements

Tested on - `Oracle JDK 8`, `Windows 11 OS`, `Docker Desktop`

### Initialize DB servers

Docker desktop is used for bringing up the db server containers. Run the below commands in command prompt.
```
docker run --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin123 -d mysql
docker run --name mongo-test -p 27017:27017 -d mongo
```

## Run

Command to start the program on Windows machine. Run it in console/command prompt only.
Maven build
```
mvnw.bat clean install   //Build the project
java -jar target\AdvProgAssignSix-1.0-jar-with-dependencies.jar   //Run the application
```
Gradle Build
```
gradlew.bat build   //Build the project
java -jar build\libs\AdvProgAssigFive-1.0-SNAPSHOT.jar   //Run the application
```

## Description
This assignment focuses on build systems such as gradle and maven. Assignment 5 project files are taken to test the two build system in this 6th assignment.

## Features
- The gradle and maven build packages the dependencies and put all of them in a single jar file which makes it very easy to run.
- The project have the build system wrappers in the project files, eliminating the need to install maven appication for building the project.


