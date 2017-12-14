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

xinput disable 'AT Translated Set 2 keyboard'
xinput disable 'TPPS/2 IBM TrackPoint'

#Those are overridden by udev...
su luca -c 'DISPLAY=:0.0 notify-send "Keyboard plugged in!"' &
su luca -c "setxkbmap us" &
su luca -c "xset r rate 150 25" &
