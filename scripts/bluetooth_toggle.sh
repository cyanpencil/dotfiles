#!/bin/bash

if [ "$EUID" -ne 0 ]
  then echo "Please run as root"
  exit
fi


pid=$(pgrep -n xinit)
pid=$(pgrep -n -P $pid)

export DBUS_SESSION_BUS_ADDRESS='unix:path=/run/user/1000/bus'
su luca -c "notify-send 'Yo, stupid guy!' 'You should totally update the backup!'"


res=$(bluetooth)
if [[ $(echo $res | grep -c "on") -ge 1 ]]; then
	su luca -c "notify-send 'Switching off bluetooth...'"
	bluetooth off
else
	su luca -c "notify-send 'Switching on bluetooth...'"
	bluetooth on
	while [[ $(bluetoothctl show | grep -c "Powered") -eq 0 ]]; do sleep 0.5; done
	bluetoothctl power on
fi
