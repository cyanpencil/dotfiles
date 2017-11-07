#!/bin/bash

#export DISPLAY=:0 
#export XAUTHORITY=/home/luca/.Xauthority
#export XAUTHORITY=/tmp/serverauth.FOXgx0jcUS

#sux luca notify-send aaaaaaaaaaa

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

if [[ $1 == "true" ]]; then
    su luca -c 'DISPLAY=:0.0 notify-send "Charger plugged in!"'
    su luca -c 'compton -i 0.8 -b -d :0'
    su luca -c 'xbacklight -set 80'
    su luca -c '~/scripts/switch_bar.sh i3blocks'
else
    su luca -c 'DISPLAY=:0 notify-send "Charger plugged off!"'
    su luca -c 'xbacklight -set 10'
    killall compton
    su luca -c '~/scripts/switch_bar.sh i3status'
fi

