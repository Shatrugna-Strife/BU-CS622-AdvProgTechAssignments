## Requirements

Tested on - `Open JDK 11`, `Ubuntu 22.04 WSL`

## Run

Command to start the program on Ubuntu. Run it in Bash Shell only.
```
./gradlew build   //Build the project
sudo java -jar build/libs/AdvProgAssigFour-1.0-SNAPSHOT.jar   //Run the application
```

## Description
An application to get the list of files created today using shell script which is called from the java program.

## Output
The files name are in the output.txt file in the project folder. It took quite some time to run the program because it had to search through the Ubuntu WSL and the Windows file space too.
There are few directories of Windows system that the program didn't have permission to search through.
```
find: ‘/mnt/c/ProgramData/Microsoft/Crypto/PCPKSP/WindowsAIK’: Permission denied
```

## Command
```
#!/bin/bash
sudo find / -type f -daystart -ctime 0 -print 2>&1 | tee output.txt
#sudo find / -type f -daystart -ctime 0 -print 2>&1 //comment
```

- #!/bin/bash - Shebang instructes the terminal to execute the script in specified interpreter program

- sudo - prefix added to run the command in elevated permission (root level).

- find - it is unix program which can be invoked as a shell command to search for file and directories across the specified directory recursively too.

- / - it is root directory, the top most directory of the os

- -type f - type flag indicates what result should be printed out. If f is chosen as the argument then the find command will output only files it has searched.

- -daystart  Measure times from the beginning of today rather than from 24 hours ago.

- -ctime 0 - Returns files whose status last changed is less than, more than or exactly n*24 hours ago. Currently I put 0.

- -print - prints the full file name on the standard output, followed by a newline.

- 2>&1 - 2>&1 sends the standard error to where ever standard output is being redirected as well.

## Image

![searchImage](./Screenshot%20(19).png)