#!/bin/bash


# if namespace given as argument, teleport there
[[ $# -eq 1 ]] && { sudo ip netns exec $1 sudo su $(whoami); exit; }

# otherwise use fzf to select from available namespaces
list="\x1b[00mnamespace\x1b[0m - \x1b[00mcity country\x1b[00m ip\n"

tput sc #save cursor position
IFS=$'\n'; for ns in $(ls /var/run/netns); do
	sudo ip netns exec $ns timeout 3 curl ipinfo.io -# > /tmp/${ns}_location  &
done
wait # wait for all spawned processes
tput rc # reload cursor position

IFS=$'\n'; for ns in $(ls /var/run/netns); do
	loc=$(cat /tmp/${ns}_location | jq '.city, .country, .ip' -r | tr ' ' '_')
	IFS=$'\n' read -d'' -r -a fields <<<$(echo "$loc")
	list=$list"\x1b[31m${ns}\x1b[0m - \x1b[33m${fields[0]:-?} ${fields[1]}\x1b[36m ${fields[2]}\n"
done

ns=$(echo -e $list | column -t -d | fzf --ansi --height 10 --margin 0,1,0 --border --reverse --inline-info --header-lines=1 --prompt "Where are we? ")

[[ -z $ns ]] && echo No location selected. && exit

ns=$(echo $ns | cut -d ' ' -f 1)

sudo ip netns exec $ns sudo su $(whoami)
