#!/bin/bash
sudo ip link set enp0s25 up
sudo dhcpcd enp0s25
