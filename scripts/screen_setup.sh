#!/bin/sh
set -x
monitors=$(cat $(echo /sys/class/drm/*/status) | grep -c '^connected')

notify-send "$monitors monitors connected."

MON="DP1"
[[ $(xrandr | grep -c "DP1-8") -eq 0 ]] && MON="DP2"

if [[ $monitors -eq 3 ]]; then
	#xrandr --output eDP-1 --primary --mode 2560x1440 --pos 0x0 --rotate normal \
		   #--output DP-1 -r 140 --mode 1920x1080 --pos 1920x0 --rotate normal \
		   #--output HDMI-2 --mode 1280x1024 --pos 3840x0 --rotate normal \
		   #--output HDMI-1 --off \
		   #--output DP-2 --off 


	xrandr --output eDP1 --mode 2560x1440 --pos 0x0 
	sleep 5
	xrandr --output ${MON}-8 --primary --auto --right-of eDP1 
	sleep 10
	xrandr --output ${MON}-1 --mode 1920x1080 --right-of DP-2-8 -r 144 --scale 1.33x1.33

elif [[ $monitors  -eq 2 ]]; then
	xrandr --output eDP1 --primary --mode 2560x1440 --pos 0x0 --rotate normal \
		   --output HDMI1 --off
	sleep 1
	xrandr --output ${MON} --auto --rotate normal --right-of eDP1
	xrandr --output ${MON}-8 --auto --rotate normal --right-of eDP1
	xrandr --output ${MON}-1 --auto --rotate normal --right-of eDP1
else
	xrandr --output eDP1 --primary --mode 2560x1440 --pos 0x0 --rotate normal \
		   --output DP1 --off \
		   --output HDMI1 --off \
		   --output DP2 --off \
		   --output HDMI2 --off \
		   --output ${MON}-8 --off \
		   --output ${MON}-1 --off

fi

