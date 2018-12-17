#!/bin/bash

xsetwacom set "Wacom Intuos S 2 Pen stylus" "Suppress" "0"


if [[ $1 == "ciao" ]]; then
    xsetwacom set "Wacom Intuos S 2 Pen stylus" "Button" "2" "key +space"
    xsetwacom set "Wacom Intuos S 2 Pen stylus" "Button" "3" "key +shift"

    xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "1" "key shift"
    xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "3" "key ctrl"
    xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "8" "key ctrl -"
    xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "9" "key ctrl shift 0"
    #xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "8" "key shift ;"
    #xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "9" "key '"
else
xsetwacom set "Wacom Intuos S 2 Pen stylus" "Button" "2" "button +3"
xsetwacom set "Wacom Intuos S 2 Pen stylus" "Button" "3" "button +2"

xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "1" "key shift ctrl e"
xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "3" "key shift ctrl p"
xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "8" "key ctrl -"
xsetwacom set "Wacom Intuos S 2 Pad pad" "Button" "9" "key ctrl +"
fi
