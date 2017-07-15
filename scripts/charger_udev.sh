#!/bin/bash

#export DISPLAY=:0 
#export XAUTHORITY=/home/luca/.Xauthority

#echo $XAUTHORITY;

#echo "ciao";

if [[ $1 == "true" ]]; then
    notify-send "Charger plugged in!"
else
    notify-send "Charger plugged off!"
fi

exit;
