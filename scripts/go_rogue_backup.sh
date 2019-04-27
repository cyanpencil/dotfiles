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

netctl stop-all
PROFILE=$(netctl list | fzf --height=10 --prompt "Select wifi profile: ")

echo "Starting profile $PROFILE"

netctl start $PROFILE

if [[ $? -ne 0 ]]; then echo "Something went wrong..."; exit 1; fi

OVPN=$(find /home -maxdepth 5 -iname "*.ovpn*" 2>/dev/null | fzf --height=10 --prompt "Select openvpn profile: ")

if [[ -z $OVPN ]]; then echo "No vpn selected..."; exit 1; fi

echo "Waiting to be connected..."

netctl wait-online $PROFILE

ip netns exec phyns openvpn $OVPN
