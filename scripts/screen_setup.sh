#!/bin/sh
if [[ $(cat $(echo /sys/class/drm/*/status) | grep -c disconnected) -le 2 ]]; then
	#xrandr --output eDP-1 --primary --mode 1920x1080 --pos 0x1080 --rotate normal --output DP-1 -r 140 --mode 1920x1080 --pos 0x0 --rotate normal --output HDMI-1 --off --output DP-2 --off --output HDMI-2 --mode 1280x1024 --pos 1920x0 --rotate left

	xrandr --output eDP-1 --primary --mode 1920x1080 --pos 0x0 --rotate normal --output DP-1 -r 140 --mode 1920x1080 --pos 1920x0 --rotate normal --output HDMI-1 --off --output DP-2 --off --output HDMI-2 --mode 1280x1024 --pos 3840x0 --rotate normal
else
	xrandr --output eDP-1 --primary --mode 1920x1080 --pos 0x0 --rotate normal --output DP-1 --off --output HDMI-1 --off --output DP-2 --off --output HDMI-2 --off
fi

