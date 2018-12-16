#!/bin/bash

pid=$(pgrep -t tty$(fgconsole) xinit)
pid=$(pgrep -P $pid -n)

import_environment() {
		(( pid )) && for var; do
				IFS='=' read key val < <(egrep -z "$var" /proc/$pid/environ)

				printf -v "$key" %s "$val"
				[[ ${!key} ]] && export "$key"
		done
}


cd /backup

last=$(find *.tar.gz -mtime -15 | wc -l)

if [[ $last -eq 0 ]]; then
	import_environment DBUS_SESSION_BUS_ADDRESS
	notify-send "Yo, stupid guy!" "You should totally update the backup!"
fi
