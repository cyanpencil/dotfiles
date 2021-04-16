#!/usr/bin/env sh

## Add this to your wm startup file.

cd "$(dirname "$0")"

# Terminate already running bar instances
killall -9 -q polybar

# Wait until the processes have been shut down
#while pgrep -u $UID -x polybar >/dev/null; do sleep 1; done


# Launch bar1 and bar2
#polybar  -r -c config.ini main  $* &
#polybar  -r -c bottom.ini top  $* &
#polybar  -r -c bottom.ini bottom  $*

NSCMD="bash -c "
[[ $(sudo ip netns) =~ "phyns" ]] && NSCMD="sudo ip netns exec phyns su luca -c "

PULSE="XDG_RUNTIME_DIR=/run/user/1000/"  # to reach pulseaudio from other netns

#!/bin/sh

if [ -z "$(pgrep -x polybar)" ]; then
	for m in $(polybar --list-monitors | cut -d":" -f1); do
		$NSCMD "MONITOR=$m $PULSE polybar -c config.ini --reload main &"
		sleep 1
	done
else
	polybar-msg cmd restart
fi
