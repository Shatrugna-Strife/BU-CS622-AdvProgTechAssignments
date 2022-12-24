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
```
mvnw.bat clean install   //Build the project
java -jar target\AdvProgAssignSix-1.0-jar-with-dependencies.jar   //Run the application
```

## Description
The application inserts data in both mysql and mongodb. It also tests the text search query on both the db.
