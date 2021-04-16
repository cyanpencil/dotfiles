#!/usr/bin/env bash

## Author  : Aditya Shakya
## Mail    : adi1090x@gmail.com
## Github  : @adi1090x
## Twitter : @adi1090x

rofi_command="rofi -theme ~/.config/polybar/scripts/rofi/bluetooth.rasi"

## Get Volume
#VOLUME=$(amixer get Master | tail -n 1 | awk -F ' ' '{print $5}' | tr -d '[]%')
MUTE=$(amixer get Master | tail -n 1 | awk -F ' ' '{print $6}' | tr -d '[]%')

active=""
urgent=""

if [[ $MUTE == *"off"* ]]; then
    active="-a 1"
else
    urgent="-u 1"
fi

if [[ $MUTE == *"off"* ]]; then
    active="-a 1"
else
    urgent="-u 1"
fi

if [[ $MUTE == *"on"* ]]; then
    VOLUME="$(amixer get Master | tail -n 1 | awk -F ' ' '{print $5}' | tr -d '[]%')%"
else
    VOLUME="Mu..."
fi

## Icons
ICON_UP=""
ICON_DOWN=""
ICON_MUTED=""

prompt="Powered: $(bluetoothctl show | grep Powered | awk '{print $2}')"

options="Mouse\nGrind wireless\nPower off"


## Main
chosen="$(echo -e "$options" | $rofi_command -p "$prompt" -dmenu -selected-row 0)"
echo $chosen > /tmp/ciao
case $chosen in
    "Grind wireless")
		sudo bluetoothctl power on && sudo bluetoothctl connect D0:8A:55:1C:78:65
        ;;
	"Mouse")
		sudo bluetoothctl power on && sudo bluetoothctl connect C2:2B:20:BE:BB:19
		;;
	"Power off")
		sudo bluetoothctl power off
		;;
esac

