#!/bin/bash

a=(0 50 100)
i=0

while [[ 1 ]]; do
	i=$((($i+1)%3))
	echo $i
	sudo xbacklight -ctrl tpacpi::kbd_backlight -set ${a[i]};
	sleep 0.4
done;
