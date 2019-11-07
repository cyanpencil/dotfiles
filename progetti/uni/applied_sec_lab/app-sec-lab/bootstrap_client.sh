#!/usr/bin/env bash

cat <<EOF | sudo tee -a /etc/hosts
192.168.42.42 webserver
EOF

apt-get update
apt-get install --no-install-recommends lubuntu-desktop -y
# the installation of the desktop environment needs a reboot
# TODO find a box with a desktop environment included
reboot now
