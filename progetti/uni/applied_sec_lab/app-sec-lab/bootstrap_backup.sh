#!/usr/bin/env bash
sudo apt-get update -y
sudo apt-get install rsyslog -y

sudo systemctl start rsyslog

sudo cat > /etc/rsyslog.conf <<EOF
# for TCP use:
module(load="imtcp") # needs to be done just once
input(type="imtcp" port="514")
module(load="imudp") # needs to be done just once
input(type="imudp" port="514")
*.* /var/log/biglog.log

EOF

#TODO generate public-private key pair
mkdir -p /var/backup/log/
mkdir -p /var/backup/db/
sudo cat > /etc/cronlog.sh <<'EOF'
#!/bin/sh

currentdate=$(date +%Y%m%d%H%M)
cp /var/log/biglog.log /var/backup/log/enclog
truncate -s 0 /var/log/biglog.log
openssl enc -aes-256-cbc -salt -in /var/backup/log/enclog -out /var/backup/log/$currentdate.log -pass file:/stronzo/public_key.pem
rm /var/log/enclog
find /var/backup/log/* -mtime +1 -exec rm {} \; # remove old files
cp /home/database/database_backup.sql.gz /var/backup/db/encdb
openssl enc -aes-256-cbc -salt -in /var/backup/db/encdb -out /var/backup/db/$currentdate.db -pass file:/stronzo/public_key.pem
rm /var/log/encdb
find /var/backup/db/* -mtime +1 -exec rm {} \;
EOF
sudo chmod +x /etc/cronlog.sh
echo "* * * * * root  /etc/cronlog.sh > /dev/null 2>&1" >  /etc/cron.d/cronlog


#start the service
sudo systemctl restart rsyslog
sudo systemctl enable rsyslog

# add database user to allow sending the backup
userdel -r database
useradd database -s /bin/bash -m
echo database:pass | sudo chpasswd
# copy database pub key (which was file-provisioned)
mkdir /home/database/.ssh
chown database:database /home/database/.ssh
mv /home/vagrant/database.pub /home/database/.ssh/authorized_keys
chown database:database /home/database/.ssh/authorized_keys


# enable user keys for ssh
sudo cat adminkey.pub >> /root/.ssh/authorized_keys
sed -i "s/#AuthorizedKeysFile/AuthorizedKeysFile/" "/etc/ssh/sshd_config"
service sshd reload

#iptables-restore < /vagrant/tables/backup.v4
#iptables-save > /etc/firewall.conf
#cat > /etc/network/if-up.d/iptables <<EOF
#!/bin/sh
#sudo iptables-restore < /etc/firewall.conf
#EOF

chmod +x /etc/network/if-up.d/iptables

echo "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant
echo -e "e3!^DW15l|iQ#g8I6l8mR1-Q\ne3!^DW15l|iQ#g8I6l8mR1-Q" | passwd root
echo -e "S6D;x}QX1(W2JcnypSAP\l9|\nS6D;x}QX1(W2JcnypSAP\l9|" | passwd vagrant
