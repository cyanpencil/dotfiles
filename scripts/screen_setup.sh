#!/bin/sh
set -x

export DBUS_SESSION_BUS_ADDRESS='unix:path=/run/user/1000/bus'
export XAUTHORITY='/home/luca/.Xauthority'
export DISPLAY=:0

monitors=$(cat $(echo /sys/class/drm/*/status) | grep -c '^connected')




runasuser=$([[ $EUID -ne 0 ]] && echo "bash -c" || echo "su luca -c")
$runasuser 'notify-send "'$monitors' monitors connected. Setting xrate+xmodmap"'
$runasuser 'xset r rate 150 49'
$runasuser 'xmodmap /home/luca/.xmodmap'


MON="DP.1"
[[ $(xrandr | grep -c "DP.1-8") -eq 0 ]] && MON="DP.2"
MON=$(xrandr | grep -o "$MON" | head -n 1)





if [[ $monitors -eq 2 ]]; then
	xrandr --output e${MON} --mode 2560x1440 --pos 0x0 
	xrandr --output ${MON}-8 --mode 2560x1440 --pos 0x0 
fi
if [[ $monitors -eq 1 ]]; then
	xrandr --output e${MON} --mode 2560x1440 --pos 0x0 
	xrandr --output ${MON}-8 --off
	xrandr --output HDMI-1 --off --output HDMI-2 --off 
fi

exit 0






### OLD





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
	xrandr --output ae${MON} --primary --mode 2560x1440 --pos 0x0 --rotate normal \
		   --output DP1 --off \
		   --output HDMI1 --off \
		   --output DP2 --off \
		   --output HDMI2 --off \
		   --output ${MON}-8 --off \
		   --output ${MON}-1 --off

fi

