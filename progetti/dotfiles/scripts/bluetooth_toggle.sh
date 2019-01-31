#!/bin/bash

res=$(bluetooth)
if [[ $(echo $res | grep -c "on") -ge 1 ]]; then
	echo "Switching off bluetooth..."
	sudo bluetooth off
else
	echo "Switching on bluetooth..."
	sudo bluetooth on
	while [[ $(sudo bluetoothctl show | grep -c "Powered") -eq 0 ]]; do sleep 1; done
	sudo bluetoothctl power on
	sudo bluetoothctl connect 00:1B:66:88:E8:12
fi
