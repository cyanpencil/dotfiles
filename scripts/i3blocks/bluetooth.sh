#!/bin/bash

res=$(bluetoothctl show)
if [[ $(echo $res | grep -c "Powered") -ge 1 ]]; then
	if [[ $BLOCK_BUTTON == 3 ]]; 
	then echo ""
	else echo ""
	fi
else
	if [[ $BLOCK_BUTTON == 3 ]]; 
	then echo ""
	else echo ""
	fi
fi

case $BLOCK_BUTTON in
	#left click
	1)
		#connect to earphones
		if [[ $(echo $res | grep -c "Powered: no") -ge 1 ]]; then
			sudo bluetoothctl power on
		fi
		notify-send "Earphones" "Attempting to connect..."
		sudo bluetoothctl connect 00:1B:66:88:E8:12
		if [[ $? == 0 ]]; then notify-send "Earphones" "Connected!"
		else notify-send "Earphones" "Failed!"
		fi
	;;
	#right click
	3)  
		bluetooth_toggle.sh &>/dev/null &
	;;
esac

