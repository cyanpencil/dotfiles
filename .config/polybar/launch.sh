#!/usr/bin/env sh

## Add this to your wm startup file.

# Terminate already running bar instances
#killall -q polybar

# Wait until the processes have been shut down
#while pgrep -u $UID -x polybar >/dev/null; do sleep 1; done

# Launch bar1 and bar2
#polybar  -r -c config.ini main  $* &
#polybar  -r -c bottom.ini top  $* &
#polybar  -r -c bottom.ini bottom  $*


#!/bin/sh

if [ -z "$(pgrep -x polybar)" ]; then
	for m in $(polybar --list-monitors | cut -d":" -f1); do
		MONITOR=$m polybar -c config.ini --reload main &
		sleep 1
	done
else
	polybar-msg cmd restart
fi
