#!/bin/bash


if [ "$EUID" -ne 0 ]
    then echo "Please run as root"
    exit
fi

plugged_in="true"

if [[ $# -eq 0 ]]; then
    plugged_in=$(acpi --ac-adapter | sed -r "s/.*: (.*)-line\$/\1/g")
else 
    plugged_in=$1
fi


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

if [[ $plugged_in == "true" ]]; then
    su luca -c 'DISPLAY=:0.0 notify-send "Charger plugged in!"'
    su luca -c '~/scripts/switch_bar.sh i3blocks'
    su luca -c 'xbacklight -set 80'
    su luca -c 'compton -i 0.8 -b'
else
    su luca -c 'DISPLAY=:0 notify-send "Charger plugged off!"'
    su luca -c 'xbacklight -set 10'
    killall compton
    su luca -c '~/scripts/switch_bar.sh i3status'
fi

