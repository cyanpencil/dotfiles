#!/bin/bash


function clean_lock {
	echo "Cleaning up..."
	rm /tmp/namespace
	exit 0
}


FZF_FLAGS="--border --reverse --margin=0,1,0 --height=10 --inline-info"

if [[ $EUID != 0 ]]; then echo "Not running as root!"; exit 1; fi


IFACE=$(iw dev | grep Interface | cut -d ' ' -f 2)
VPN_DNS="10.4.0.1"

if [[ ${#IFACE} -eq 0 ]]; then 
	IFACE=$(ip netns exec phyns iw dev | grep Interface | cut -d ' ' -f 2)
	if [[ ${#IFACE} -eq 0 ]]; then echo "Wifi interface not found!"; exit 1; fi
else 
	if [[ $(ip netns | grep phyns -c) -eq 0 ]]; then 
		echo "Namespace phyns not found; creating it..."
		ip netns add phyns
		mkdir /etc/netns/phyns
	fi

	echo "Setting dns nameserver $VPN_DNS"
	echo "nameserver $VPN_DNS" > /etc/resolv.conf
	echo "Moving $IFACE to namespace phyns"
	iw phy0 set netns name phyns
fi

while [[ -f /tmp/namespace ]]; do echo "Waiting for /tmp/namespace lock..."; sleep 2; done
touch /tmp/namespace
chmod 777 /tmp/namespace
trap clean_lock INT

if [[ $# -eq 1 ]]; then 
	if [[ $(ip netns | grep $1 -c) -eq 0 ]]; then 
		ip netns add $1
		mkdir /etc/netns/$1
		echo "nameserver $VPN_DNS" > /etc/netns/$1/resolv.conf
	fi
	echo -e "Setting up on namespace \x1b[31m$1\x1b[0m"
	echo $1 > /tmp/namespace
	OVPN=$(fd . /home -d 5 -e ovpn --no-ignore | fzf $FZF_FLAGS --prompt "Select openvpn profile: ")
else
	PROFILE=$(netctl list | fzf $FZF_FLAGS --color=prompt:196 --prompt "Select wifi profile: " | tr '*+' '  ')
	OVPN=$(fd . /home -d 5 -e ovpn --no-ignore | fzf $FZF_FLAGS --prompt "Select openvpn profile: ")

    if [[ $(ip l | grep -c wg0) -ge 1 ]]; then
		echo "Wireguard is already set up."
	else 
		WIREGUARD=$(echo -e "true\nfalse" | fzf $FZF_FLAGS --prompt "Do you want to setup wg0 too?")
	fi

	if [[ -z $OVPN ]]; then echo "No vpn selected..."; rm /tmp/namespace; exit 1; fi

	if [[ ! $(netctl is-active $PROFILE) == "active" ]]; then
		netctl stop-all
		echo "Starting profile $PROFILE"
		netctl start $PROFILE
		if [[ $? -ne 0 ]]; then echo "Something went wrong..."; rm /tmp/namespace; exit 1; fi
		echo "Waiting to be connected..."
		netctl wait-online $PROFILE
	else 
		echo "Network already set up."
	fi 

	[[ $WIREGUARD == "true" ]] && wireguard.sh
fi


ip netns exec phyns openvpn  $OVPN
