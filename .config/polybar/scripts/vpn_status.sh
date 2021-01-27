#!/bin/bash
# author: github.com/cyanpencil

format=0

toggle() {
	format=$(((format + 1) % 3))
	echo ciao >> /tmp/vpn_ip_show
}

refresh() {
	a=$(sudo ip netns exec default curl ipinfo.io -s)
	readarray -t a  < <(echo $a | jq '.ip, .city, .country' -r )
	[[ "$a" =~ "rate limit" ]] && echo "rate limit" && exit 0
}

trap "toggle" USR1

echo "Loading vpn location..."

refresh
while true; do
	[[ "$format" -eq "2" ]] && echo "${a[2]}"
	[[ "$format" -eq "1" ]] && echo "${a[1]} ${a[2]}"
	[[ "$format" -eq "0" ]] && echo "${a[1]} ${a[2]}%{F#a0a0a0} ${a[0]} "

	sleep 300  &
	wait 

	[[ $? -eq 0 ]] && refresh  # refresh only if the wait completed
done
