# Applied security lab project

## Getting started

1. install vagrant [https://www.vagrantup.com/](https://www.vagrantup.com/)
2. go in the directory
3. start all VMs with `vagrant up`
  - `vagrant up client`
4. in the client desktop environment
  - wait until started, provisioned and rebooted
  - maybe `startx`
  - login with user `vagrant` and password `vagrant`
5. on the client go on to the url `https://ca.imovies.com`
6. suspend all VMs with `vagrant suspend`

## If the client does't boot
- disable USB for that machine in virtualbox and then `vagrant up client` again
- if it doesn't have its ip anymore `sudo ip a add 10.42.7.7/24 dev eth1`

## About CA admin
- the admin can only sign in via certificate, therefore I hacked around a bit: the OU (organisation unit) of the certificate is '1' for a CA admin and '0' for the others
- created a CA admin cert and put it in the client folder
- changed the initial serial in the core to avoid having a collision
- the password for the admin user is admin123, although should not be useful/needed

## sysadmin
- from client `ssh ca.imovies.com`, key passphrase `hunter2`
- then `ssh -lroot <host>` for `webserver`, `core`, `database` or `backup`

## Useful stuff
- `sudo tail /var/log/apache2/error.log -f`
- `sudo journalctl -ef -u apt.service`
- `mysql -uroot -p'H9-$7ht^wkY^-GRd%^4SD=dN'`

## Passwords freshly generated for our machines:

- `clientkey: ZQTej)nl]AVPW{N9{l8sylds!!gux6ZY65,Hg-v7`
- `adminkey: PA>*BCf)L~Jc\o;/@!V|9i$[JC+9op+-##ct1/8{`
- `firewall: Fd7x'GJ>kN]HQZ%<mb/~`
- `webserver: 1$y'XZ|*9@_)P7RcZCMQ`
- `ca-core: {8P;a.vw@f/U=V@uZ%zQ`
- `database: u<X4?bWI3Fe.2Ct9Vxqp`
- `adminserver: 'l<g0rLMBfem]d7K6'Y$0~~+`
- `backup: S6D;x}QX1(W2JcnypSAP\l9|`
- `database mysql root: H9-$7ht^wkY^-GRd%^4SD=dN`

## Root Passwords

- `firewall: g$Nyf=Jril$qomnME7K+#PSi`
- `webserver: EQgn!waqCBs5Pewc8gxP#jwA`
- `ca-core: g64?S+8&tZBEseYq*J@Iz%Dw`
- `database: zWah7H1BLFbs-kz#J-LYe2rA`
- `adminserver: |C1uJ*?kMG1ul0F-GeDOcBBo`
- `backup: e3!^DW15l|iQ#g8I6l8mR1-Q`
