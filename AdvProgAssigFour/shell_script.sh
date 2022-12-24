#!/bin/bash
sudo find / -type f -daystart -ctime 0 -print 2>&1 | tee output.txt
#sudo find / -type f -daystart -ctime 0 -print 2>&1
