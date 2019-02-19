#!/bin/bash

if [[ $(pacman -Q i3lock | grep -c "color") -eq 0 ]]; then echo "Must have i3lock-color. Aborting..."; exit 0; fi

read  -a img <<< $(find /home/luca/img/wallpaper_lock -iname "*.png" | tr '\n' ' ')
imglen=${#img[@]}
imgfile=${img[ $(( RANDOM % imglen )) ]}


	# suspend dunst
pkill -u $USER -USR1 dunst


i3lock -n -i $imgfile \
	--insidecolor=373445ff --ringcolor=ffffffff --line-uses-inside \
	--keyhlcolor=d23c3dff --bshlcolor=d23c3dff --separatorcolor=00000000 \
	--insidevercolor=fecf4dff --insidewrongcolor=d23c3dff \
	--ringvercolor=ffffffff --ringwrongcolor=ffffffff --indpos="x+86:y+1003" \
	--radius=15 --veriftext="" --wrongtext=""

	# resume dunst
pkill -u $USER -USR2 dunst
