#!/bin/bash
if [[ $(pgrep picom -xc) -eq "0" ]]; then 
	polybar-msg hook picom-toggle 2
	echo ciao
	picom -b -i 0.9
else
	polybar-msg hook picom-toggle 1
	pkill -9 picom
fi
