#!/bin/bash
if [[ $(bluetoothctl show | grep Powered | awk '{print $2}') =~ "yes" ]]; then 
	echo "%{B#6c77bb} bt "
else
	echo "%{B#9e9e9e} bt "
fi

# 
