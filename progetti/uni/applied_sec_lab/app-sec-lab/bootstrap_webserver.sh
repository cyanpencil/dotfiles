#!/usr/bin/env bash

cat <<EOF | sudo tee -a /etc/hosts
10.42.43.4 database
10.42.43.3 core
10.42.44.2 backup
EOF

apt-get update
apt-get install -y apache2
apt-get install -y libapache2-mod-php7.0
apt-get install -y php7.0
apt-get install -y php7.0-mysql
apt-get install -y php7.0-curl
apt-get install -y mysql-client # dev
apt-get install -y rsyslog

# https://blog.rapid7.com/2017/10/04/how-to-password-protect-apache-directories-with-mod_authn_dbd-and-mysql-on-ubuntu-linux/
apt-get install -y libaprutil1-dbd-mysql
a2enmod dbd authn_dbd socache_shmcb authn_socache ssl

# folder for crl
mkdir /etc/apache2/crl
chown www-data:www-data /etc/apache2/crl
chmod 755 /etc/apache2/crl
cp /vagrant/webserver/ca.crl /etc/apache2/crl/cde07a2c.r0

# need to reload apache2 to update the crl :-/
# so we do that periodically
echo "* * * * * root  /usr/sbin/apachectl graceful > /dev/null 2>&1" >  /etc/cron.d/cronlog

rm -rf /home/vagrant/certs2
cp -a /home/vagrant/certs/ /home/vagrant/certs2
rm -rf /var/www/html2
cp -a /var/www/html/ /var/www/html2

# replace apache config
cat <<EOF | sudo tee /etc/apache2/sites-available/000-default.conf
DocumentRoot /var/www/html2
ServerName ca.imovies.com

DBDriver mysql
DBDParams "host=database dbname=imovies user=webserver pass=webserverpass"
DBDMin 4
DBDKeep 8
DBDMax 20
DBDExptime 300

<VirtualHost *:443>
  ErrorLog /var/log/apache2/error.log
  CustomLog /var/log/apache2/access.log combined

  SSLEngine On
  SSLCertificateFile /home/vagrant/certs2/webserver.crt
  SSLCertificateKeyFile /home/vagrant/certs2/webserver.key
  SSLCACertificateFile /home/vagrant/certs2/cacert.pem
  SSLCARevocationPath /etc/apache2/crl/
  SSLCARevocationCheck chain

  <Location "/crt/">
    SSLOptions +StdEnvVars
    SSLVerifyClient require
    SSLVerifyDepth  5
  </Location>

  <Location "/pwd/">
    AuthType Basic
    AuthName "IMovies CA"
    AuthBasicProvider socache dbd
    AuthnCacheProvideFor dbd
    AuthnCacheContext my-server

    Require valid-user
    AuthDBDUserPWQuery "SELECT CONCAT('{SHA}',TO_BASE64(UNHEX(pwd))) FROM users WHERE uid = %s"
  </Location>
</VirtualHost>

<Directory "/var/www/html2/common">
  Order allow,deny
  Deny from all
</Directory>
EOF

echo "export WEB_PASS=webserverpass" >> /etc/apache2/envvars

service apache2 restart

# rsyslog send to backup (old config format!)
cat <<'EOF' | sudo tee /etc/rsyslog.d/apache_access.conf
$ModLoad imfile
$InputFileName /var/log/apache2/access.log
$InputFileTag accesslog
$InputFileStateFile accesslog
$InputFileSeverity error
$InputFileFacility local6
$InputRunFileMonitor
$InputFilePollInterval 10
local6.*        @@backup:514
EOF
cat <<'EOF' | sudo tee /etc/rsyslog.d/apache_error.conf
$ModLoad imfile
$InputFileName /var/log/apache2/error.log
$InputFileTag errorlog
$InputFileStateFile errorlog
$InputFileSeverity error
$InputFileFacility local6
$InputRunFileMonitor
$InputFilePollInterval 10
local6.*        @@backup:514
EOF

systemctl restart rsyslog

# TODO
# disable server signature in /etc/apache2/conf-enabled/security.conf, cf https://www.guru99.com/apache.html

# admin ssh key
sudo cat adminkey.pub >> /root/.ssh/authorized_keys
sed -i "s/#AuthorizedKeysFile/AuthorizedKeysFile/" "/etc/ssh/sshd_config"
service sshd reload

sudo iptables-restore < /vagrant/tables/webserver.v4
sudo iptables-save > /etc/firewall.conf
sudo cat > /etc/network/if-up.d/iptables <<EOF
!/bin/sh
sudo iptables-restore < /etc/firewall.conf
EOF

sudo chmod +x /etc/network/if-up.d/iptables

sudo echo "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant

echo -e "EQgn!waqCBs5Pewc8gxP#jwA\nEQgn!waqCBs5Pewc8gxP#jwA" | passwd root
echo -e "1$y'XZ|*9@_)P7RcZCMQ\n1$y'XZ|*9@_)P7RcZCMQ" | passwd vagrant

# mysql ssl cert
cp -r /vagrant/core/certs mysql_certs
cp /vagrant/core/core_sync/cacert.pem mysql_certs/ca.pem
chown -R www-data:www-data mysql_certs
