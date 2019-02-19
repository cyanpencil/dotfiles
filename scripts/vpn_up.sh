#!/bin/bash

	# horrible hack to give the "default" name to init's namespace
ln -s /proc/1/ns/net /var/run/netns/default

	# move the tun0 interface from phyns to default namespace
ip netns exec phyns ip l s tun0 netns 1
ip netns exec default ip l s tun0 up

	# set addresses and subnet masks
ip netns exec default ip a add dev tun0 ${ifconfig_local}/${ifconfig_netmask} \
	broadcast $ifconfig_broadcast
ip netns exec default ip -6 a add dev tun0 ${ifconfig_ipv6_local}/${ifconfig_ipv6_netbits}

	# set routes to go through tun0
ip netns exec default ip r a default dev tun0 via $route_vpn_gateway
ip netns exec default ip -6 r a default dev tun0

. /etc/openvpn/update-resolv-conf
