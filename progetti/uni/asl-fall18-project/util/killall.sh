#!/bin/bash

addr=(
	"192.168.56.102"
	"192.168.56.103" 
	"192.168.56.104" 
	"192.168.56.105"
	"storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip5.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip7.westeurope.cloudapp.azure.com"
	"storewz2nt2k6rryc2sshpublicip8.westeurope.cloudapp.azure.com"
)

for a in "${addr[@]}"; do
	echo $a
	ssh osboxes@$a "killall memcached memtier_benchmark java javac" &>/dev/null &
done
