#!/usr/bin/env bash
sudo apt-get update -y
sudo apt-get install rsyslog -y

# enable port forwarding
# https://www.digitalocean.com/community/tutorials/how-to-forward-ports-through-a-linux-gateway-with-iptables
echo 1 | sudo tee /proc/sys/net/ipv4/ip_forward
sysctl -p
sysctl --system
sed -i "s/#net.ipv4.ip_forward/net.ipv4.ip_forward/" "/etc/sysctl.conf"

sudo systemctl start rsyslog

sudo cat > /etc/rsyslog.d/backup.conf <<EOF
*.*  action(type="omfwd" target="10.42.44.2" port="514" protocol="tcp"
			action.resumeRetryCount="100"
			queue.type="linkedList" queue.size="10000")
EOF

#start the service
sudo systemctl restart rsyslog
sudo systemctl enable rsyslog

#Drop everything unless it's whitelisted
sudo iptables -P INPUT DROP
#sudo iptables -P FORWARD DROP
sudo iptables -P OUTPUT DROP

##basic fowarding rules
##https
sudo iptables -A FORWARD -i enp0s8 -o enp0s9 -p tcp --syn --dport 443 -m conntrack --ctstate NEW -j LOG
sudo iptables -A FORWARD -i enp0s8 -o enp0s9 -p tcp --syn --dport 443 -m conntrack --ctstate NEW -j ACCEPT
sudo iptables -A FORWARD -i enp0s8 -o enp0s9 -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
sudo iptables -A FORWARD -i enp0s9 -o enp0s8 -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
sudo iptables -t nat -A PREROUTING -i enp0s8 -p tcp --dport 443 -j DNAT --to-destination 10.42.42.42
sudo iptables -t nat -A POSTROUTING -o enp0s9 -p tcp --dport 443 -d 10.42.42.42 -j SNAT --to-source 10.42.42.1

sudo iptables -t nat -A PREROUTING -i enp0s8 -p tcp --dport 22 -j DNAT --to-destination 10.42.45.2:22
sudo iptables -t nat -A POSTROUTING -j MASQUERADE

sudo iptables -A INPUT -p tcp --dport 22 -j ACCEPT
sudo iptables -A OUTPUT -p tcp --sport 22 -j ACCEPT
#iptables -A FORWARD -p tcp --dport 22 -j ACCEPT

##protection from bruteforcing
sudo iptables -I INPUT -p tcp --dport 22 -i enp0s8 -m state --state NEW -m recent --set
sudo iptables -I INPUT -p tcp --dport 22 -i enp0s8 -m state --state NEW -m recent --update --sec 600 --hitcount 100 -j DROP

#ssh session for maintenance
sudo iptables -A INPUT -i enp0s10 -p tcp --dport 22 -m state --state NEW,ESTABLISHED -j ACCEPT
sudo iptables -A OUTPUT -o enp0s10 -p tcp --sport 22 -m state --state ESTABLISHED -j ACCEPT

#port 514 used by syslog-ng
sudo iptables -A OUTPUT -o enp0s10 -p tcp -m state --state NEW,ESTABLISHED -m tcp --dport 514 -j ACCEPT
sudo iptables -A INPUT -i enp0s10 -p tcp -m state --state ESTABLISHED -m tcp --dport 514 -j ACCEPT
sudo iptables -A OUTPUT -o enp0s10 -p tcp -m state --state NEW,ESTABLISHED -m tcp --sport 514 -j ACCEPT
sudo iptables -A INPUT -i enp0s10 -p tcp -m state --state ESTABLISHED -m tcp --sport 514 -j ACCEPT
#logging
sudo iptables -N LOGGING
sudo iptables -A INPUT -j LOGGING
sudo iptables -A LOGGING -m limit --limit 2/min -j LOG --log-prefix "IPTables Packet Dropped: " --log-level 7
sudo iptables -A LOGGING -j DROP

sudo iptables-save > /etc/firewall.conf
sudo cat > /etc/network/if-up.d/iptables <<EOF
#!/bin/sh
sudo iptables-restore < /etc/firewall.conf
EOF

sudo chmod +x /etc/network/if-up.d/iptables


# admin ssh key
sudo cat adminkey.pub >> /root/.ssh/authorized_keys
sed -i "s/#AuthorizedKeysFile/AuthorizedKeysFile/" "/etc/ssh/sshd_config"
service sshd reload

# sudo cat "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant

echo -e "g$Nyf=Jril$qomnME7K+#PSi\ng$Nyf=Jril$qomnME7K+#PSi" | passwd root
#echo -e "Fd7x'GJ>kN]HQZ%<mb/~\nFd7x'GJ>kN]HQZ%<mb/~" | passwd vagrant
