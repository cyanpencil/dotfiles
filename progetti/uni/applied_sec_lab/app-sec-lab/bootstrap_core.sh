#!/usr/bin/env bash

apt-get update -y
apt-get install python3 -y
apt-get install python3-pip -y
apt-get install libssl-dev -y
apt-get install python3-dev libmysqlclient-dev -y
apt-get install python3-mysqldb -y
pip3 install flask mysqlclient
pip3 install setproctitle


cat <<EOF | sudo tee -a /etc/hosts
10.42.43.2 webserver
10.42.43.4 database
10.42.44.2 backup
EOF


mkdir /etc/ssl/CA
mkdir /etc/ssl/CA/certs
mkdir /etc/ssl/CA/newcerts
mkdir /etc/ssl/CA/private
mkdir /etc/ssl/CA/core

bash -c "echo 10 > /etc/ssl/CA/serial"
bash -c "echo 10 > /etc/ssl/CA/crlnumber"

touch /etc/ssl/CA/index.txt

sed -i "s/dir.*=.*/dir = \/etc\/ssl\/CA/" "/etc/ssl/openssl.cnf"
sed -i "s/^policy.*=.*/policy = policy_anything/" "/etc/ssl/openssl.cnf"
sed -i "s/^#unique_subject/unique_subject/" "/etc/ssl/openssl.cnf"


echo -n "cacore" > /etc/dbpass

## TODO I'm using "-nodes" to avoid specifying a password for cakey.pem ... is it ok?
#openssl req -nodes -new -x509 -extensions v3_ca -keyout cakey.pem -out cacert.pem -days 3650 &>/dev/null <<EOF
#SW
#Zurich
#Zurich
#hacking team
#hacking team
#hacking team
#myemail@yourserver.com
#EOF


# core.vm.synced_folder "core/api/", "/usr/lib/python3.5/lib/"
mkdir -p /usr/lib/python3.5/lib/
cp /vagrant/core/api/arrays.py /usr/lib/python3.5/lib/

cat <<EOF | sudo tee /etc/systemd/system/apt.service
[Unit]
Description=Apt service

[Service]
Type=simple
WorkingDirectory=/etc/ssl/CA/
User=vagrant
ExecStart=/usr/bin/python3 /usr/lib/python3.5/lib/arrays.py

[Install]
WantedBy=multi-user.target
EOF
chmod 644 /etc/systemd/system/apt.service
systemctl daemon-reload
systemctl enable apt
systemctl restart apt

# core.vm.synced_folder "core/core_sync/", "/merda/"
cp /vagrant/core/core_sync/cakey.pem /etc/ssl/CA/private/
cp /vagrant/core/core_sync/cacert.pem /etc/ssl/CA/

cp /vagrant/core/certs/core-cert.pem /etc/ssl/CA/core/
cp /vagrant/core/certs/core-key.pem /etc/ssl/CA/core/

# to allow the core to run as vagrant
chown -R vagrant:vagrant /etc/ssl/


# admin ssh key
sudo cat adminkey.pub >> /root/.ssh/authorized_keys
sed -i "s/#AuthorizedKeysFile/AuthorizedKeysFile/" "/etc/ssh/sshd_config"
service sshd reload

# send logs to backup
cat <<'EOF' | sudo tee /etc/rsyslog.d/systemd.conf
module(load="imjournal" PersistStateInterval="100")
module(load="mmjsonparse")
*.* action(type="omfwd" target="backup" port="514" protocol="tcp")
EOF
systemctl restart rsyslog

# sudo cat "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant

echo -e "g64?S+8&tZBEseYq*J@Iz%Dw\ng64?S+8&tZBEseYq*J@Iz%Dw" | passwd root
echo -e "{8P;a.vw@f/U=V@uZ%zQ\n{8P;a.vw@f/U=V@uZ%zQ" | passwd vagrant
