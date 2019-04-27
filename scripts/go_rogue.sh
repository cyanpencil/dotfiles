#!/bin/bash

if [[ $EUID != 0 ]]; then echo "Not running as root!"; exit 1; fi


IFACE=$(iw dev | grep Interface | cut -d ' ' -f 2)

if [[ ${#IFACE} -eq 0 ]]; then 
	IFACE=$(ip netns exec phyns iw dev | grep Interface | cut -d ' ' -f 2)
	if [[ ${#IFACE} -eq 0 ]]; then echo "Wifi interface not found!"; exit 1; fi
else 
	if [[ $(ip netns | grep phyns -c) -eq 0 ]]; then 
		echo "Namespace phyns not found; creating it..."
		ip netns add phyns
	fi

	echo "Moving $IFACE to namespace phyns"
	iw phy0 set netns name phyns
fi

if [[ $# -eq 1 ]]; then 
	echo -e "Setting up on namespace \x1b[31m$1\x1b[0m"
	echo $1 > /tmp/namespace
fi

PROFILE=$(netctl list | fzf --height=10 --prompt "Select wifi profile: " | tr '*' ' ')
OVPN=$(fd . /home -d 5 -e ovpn --no-ignore | fzf --height=10 --prompt "Select openvpn profile: ")

if [[ -z $OVPN ]]; then echo "No vpn selected..."; exit 1; fi

netctl stop-all

echo "Starting profile $PROFILE"

netctl start $PROFILE

if [[ $? -ne 0 ]]; then echo "Something went wrong..."; exit 1; fi

echo "Waiting to be connected..."

netctl wait-online $PROFILE


ip netns exec phyns openvpn $OVPN
