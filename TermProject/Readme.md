## Requirements

Tested on - `Python 3.10`, `Windows 11 home`

## Run

Commands to start the program on Ubuntu. Run it in Bash Shell only.
```
pip install -r requirements.txt // to install all the dependencies

python -m flask --app main run --host=0.0.0.0  // to start the application
```

## Description
A flask application to query videos from youtube, Instagram and tiktok and provide a platform to download them too. The downloaded video is processed and broken down to multiple scenes usign opencv scene detection and can be displayed individually.