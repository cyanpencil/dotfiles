#!/bin/bash
VM_ADDRS="
osboxes@storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip5.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip7.westeurope.cloudapp.azure.com
osboxes@storewz2nt2k6rryc2sshpublicip8.westeurope.cloudapp.azure.com
"
for addr in $VM_ADDRS; do

	ssh $addr -t  "

	cat /etc/hostname

	sudo apt-get update
	sudo apt-get install --yes memcached git unzip ant openjdk-8-jdk
	wget https://github.com/RedisLabs/memtier_benchmark/archive/master.zip
	unzip master.zip
	cd memtier_benchmark-master
	sudo apt-get install --yes build-essential autoconf automake libpcre3-dev libevent-dev pkg-config zlib1g-dev dstat iperf3
	autoreconf -ivf
	./configure
	make
	sudo service memcached stop
	sudo service memcached disable
	sudo systemctl stop memcached 
	sudo systemctl disable memcached 
	echo 'ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOPTdrTdt+bQrFOuLYrKzZ6lMhVM/bc+48oW8yhJIGWo0Mkhfd8mWNc3k4Did3yyMvuMbc/9fySAC30jXef9UumbnxvMMwvmNKPkgd0UyRhWtNiLqg7gxk4Jzs7Hw/ker70s6Rs1nUWUJiBTk65ssm7eWqLzmLKFwm3D4PiDq8S15ajBdbAuV+qtGSqlQwVPHZvVqtq/g6aep5AJNu7iYdm4ZSbJ8ECR9QMampS2lcOrVWe5yjxi8o6BtQzDvv9EaIUjsdrJxAPAziRKXKQrRqme0B+LZJ5A6ycNnvoiX8+ii6jlUTm5bPtASWvIlR22A7IKCpdovJwbiO+DPPAWEh luca@vps586065.ovh.net' >> .ssh/authorized_keys
	" &

	#rsync -ra ~/asl-fall18-project $addr:~/ --info=progress2
done
