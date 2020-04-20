#!/usr/bin/env bash


cat <<EOF | sudo tee -a /etc/hosts
10.42.43.3 core
10.42.44.2 backup
EOF


sudo apt-get update -y
sudo apt-get install rsyslog -y

# TODO change root password
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password H9-$7ht^wkY^-GRd%^4SD=dN'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password H9-$7ht^wkY^-GRd%^4SD=dN'
apt-get install mysql-server -y
# TODO create new user
# in the meantime: https://stackoverflow.com/questions/14779104/how-to-allow-remote-connection-to-mysql

sed -i "s/bind-address/#bind-address/" "/etc/mysql/mysql.conf.d/mysqld.cnf"
systemctl restart mysql

# create database
mysqladmin -uroot -p'H9-$7ht^wkY^-GRd%^4SD=dN' drop imovies -f
mysqladmin -uroot -p'H9-$7ht^wkY^-GRd%^4SD=dN' create imovies
mysql -uroot -p'H9-$7ht^wkY^-GRd%^4SD=dN' imovies < /opt/database/imovies_users.dump
mysql -uroot -p'H9-$7ht^wkY^-GRd%^4SD=dN' imovies<<EOF
CREATE USER 'ca'@'10.42.43.3' IDENTIFIED BY 'cacore' REQUIRE SSL;
CREATE USER 'webserver'@'10.42.43.2' IDENTIFIED BY 'webserverpass' REQUIRE SSL;
GRANT ALL PRIVILEGES ON imovies.certificates TO 'ca'@'10.42.43.3';
GRANT ALL PRIVILEGES ON imovies.users TO 'ca'@'10.42.43.3';
GRANT SELECT ON imovies.certificates TO 'webserver'@'10.42.43.2';
GRANT SELECT ON imovies.users TO 'webserver'@'10.42.43.2';
FLUSH PRIVILEGES;
EOF

# copy certs
cp /vagrant/core/core_sync/cacert.pem /var/lib/mysql/ca.pem
cp /vagrant/core/certs/database-cert.pem /var/lib/mysql/server-cert.pem
cp /vagrant/core/certs/database-key.pem /var/lib/mysql/server-key.pem
# cp /vagrant/core/certs/webserver-cert.pem /var/lib/mysql/client-cert.pem
# cp /vagrant/core/certs/webserver-key.pem /var/lib/mysql/client-key.pem

cat <<EOF | sudo tee /etc/mysql/conf.d/mysql.cnf
[mysqld]
general_log_file = /var/log/mysql/mysql.log
general_log = 1
ssl-ca=/var/lib/mysql/ca.pem
ssl-cert=/var/lib/mysql/server-cert.pem
ssl-key=/var/lib/mysql/server-key.pem
skip-name-resolve
EOF
sudo systemctl restart mysql


cat <<EOF | sudo tee /etc/rsyslog.d/mysql.conf
module(load="imfile" PollingInterval="10")
input(type="imfile"
	File="/var/log/mysql/mysql.log"
	Tag="myapp/my.log")
*.*  action(type="omfwd" target="backup" port="514" protocol="tcp"
            action.resumeRetryCount="100"
            queue.type="linkedList" queue.size="10000")
EOF

# start syslog
sudo systemctl restart rsyslog
sudo systemctl enable rsyslog

# setup ssh key
# ssh-keygen -t ecdsa -b 521 -f id_ecdsa
cp /opt/database/keys/id_ecdsa /home/vagrant/.ssh/
chown vagrant:vagrant /home/vagrant/.ssh/id_ecdsa

# automatic backup
echo "* * * * * vagrant  /opt/database/backup.sh > /dev/null 2>&1" >>  /etc/cron.d/backup


# admin ssh key
sudo cat adminkey.pub >> /root/.ssh/authorized_keys
sed -i "s/#AuthorizedKeysFile/AuthorizedKeysFile/" "/etc/ssh/sshd_config"
service sshd reload

# sudo cat "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant

echo -e "zWah7H1BLFbs-kz#J-LYe2rA\nzWah7H1BLFbs-kz#J-LYe2rA" | passwd root
#echo -e "u<X4?bWI3Fe.2Ct9Vxqp\nu<X4?bWI3Fe.2Ct9Vxqp" | passwd vagrant
