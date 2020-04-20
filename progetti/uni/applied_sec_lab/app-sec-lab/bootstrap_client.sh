#!/usr/bin/env bash

cat <<EOF | sudo tee -a /etc/hosts
10.42.7.8 ca.imovies.com
EOF

cat /vagrant/client/keys/clientkey > .ssh/id_ecdsa

cat <<EOF | sudo tee -a /etc/xdg/autostart/firefox.desktop
[Desktop Entry]
Type=Application
Name=Firefox
Exec=firefox https://ca.imovies.com
Terminal=false
EOF

cp cert8.db /home/vagrant/.mozilla/firefox/a1bgszwk.default/cert8.db
chmod 777 /home/vagrant/.mozilla/firefox/a1bgszwk.default/cert8.db
