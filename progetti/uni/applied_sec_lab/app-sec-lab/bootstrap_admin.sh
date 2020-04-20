#!/usr/bin/env bash


cat <<EOF | sudo tee -a /etc/hosts
10.42.44.1 firewall
10.42.44.2 backup
10.42.44.3 database
10.42.44.4 core
10.42.44.5 webserver
EOF


apt-get update
sudo mv adminkey .ssh/id_ecdsa
chmod 600 .ssh/id_ecdsa
sudo ssh-add

sudo cat /vagrant/client/keys/clientkey.pub > .ssh/authorized_keys

# sed -i "s/PasswordAuthentication no/PasswordAuthentication yes/" "/etc/ssh/sshd_config"
# sed -i "s/PermitRootLogin prohibit-password/PermitRootLogin yes/" "/etc/ssh/sshd_config"
# systemctl restart sshd

#sudo iptables-restore < /vagrant/tables/admin.v4
#sudo iptables-save > /etc/firewall.conf
#sudo cat > /etc/network/if-up.d/iptables <<EOF
#!/bin/sh
#sudo iptables-restore < /etc/firewall.conf
#EOF

sudo chmod +x /etc/network/if-up.d/iptables

echo "vagrant ALL=(ALL) ALL" > /etc/sudoers.d/vagrant

echo -e "|C1uJ*?kMG1ul0F-GeDOcBBo\n|C1uJ*?kMG1ul0F-GeDOcBBo" | passwd root
echo -e "'l<g0rLMBfem]d7K6'Y$0~~+\n'l<g0rLMBfem]d7K6'Y$0~~+" | passwd vagrant
