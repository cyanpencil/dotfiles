#!/bin/bash


#remap j k to mouse buttons while using trackpoint
xkbset m

while [[ 1 ]]
do
    sudo timeout 0.5s evtest /dev/input/event20 > touchscreen_log.txt
    TRACKLOG=$(cat touchscreen_log.txt | grep time | wc -l)
    if [[ $TRACKLOG -ge 1 ]]; then
        xmodmap -e "keycode 31 = Pointer_Button1"
        xmodmap -e "keycode 32 = Pointer_Button3"
        #xmodmap -e "keycode 33 = Pointer_Button2"
    else
        xmodmap -e "keycode 31 = i"
        xmodmap -e "keycode 32 = o"
        #xmodmap -e "keycode 33 = p"
    fi
done
