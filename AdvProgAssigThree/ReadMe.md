## Requirements

Tested on - `Oracle JDK 8`, `Windows 11 OS`

## Run

Command to start the program on Windows machine. Run it in console/command prompt only.
```
gradlew.bat build   //Build the project
java -jar build\libs\AdvProgAssigThree-1.0-SNAPSHOT.jar   //Run the application
```

## Description
The application does the performance comparison between the brute force and lucene indexing method for retrieving documents.

### Application

- The application creates a data folder in the project which stores the lucene indexing data
- The application will search through multiple number of articles ranging from 10 to 10000, and plots the time taken on the graph
- For graph plotting JfreeChart java plotting library is used for easy plotting of the data.

### Images
Red - Brute Force method\
Blue - Lucene Indexing

![searchImage](./Screenshot%20(14).png)

