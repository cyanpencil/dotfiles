#!/bin/bash
if [[ $(pgrep xautolock -xc) -eq "0" ]]; then 
	polybar-msg hook lock-toggle 2
	xautolock -time 2 -corners "+---" -cornerdelay 5 \
			  -notifier 'dim_screen.sh' -notify 10 \
			  -locker "lock.sh" & disown
else
	polybar-msg hook lock-toggle 1
	pkill -9 xautolock
fi
