#!/bin/bash

#sleep 1  #to wait for udev to finish activating keyboard #does not work...

pid=$(pgrep -t tty$(fgconsole) xinit)
pid=$(pgrep -P $pid -n)

import_environment() {
        (( pid )) && for var; do
                IFS='=' read key val < <(egrep -z "$var" /proc/$pid/environ)

                printf -v "$key" %s "$val"
                [[ ${!key} ]] && export "$key"
        done
}

import_environment XAUTHORITY USER DISPLAY DBUS_SESSION_BUS_ADDRESS


if [[ $1 -eq 0 ]]; then
	xinput disable 'AT Translated Set 2 keyboard'
	#xinput disable 'TPPS/2 IBM TrackPoint' #disabled to make touchpad buttons work
	su luca -c 'notify-send "Keyboard plugged in!"' &
	su luca -c "setxkbmap us" &
	su luca -c "xset r rate 150 25" &
else
	xinput enable 'AT Translated Set 2 keyboard'
	xinput enable 'TPPS/2 IBM TrackPoint'
	su luca -c 'notify-send "Keyboard plugged out!"' &
	su luca -c "setxkbmap us" &

fi
#Those are overridden by udev...
