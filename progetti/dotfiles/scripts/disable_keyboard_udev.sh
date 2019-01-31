#!/bin/sh
if [[ $(cat /tmp/keyboard_plugged) -eq $1 ]]; then exit; fi
echo $1 > /tmp/keyboard_plugged
(/home/luca/scripts/disable_keyboard.sh $1 &) & exit
