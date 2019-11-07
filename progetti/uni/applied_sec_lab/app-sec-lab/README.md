# Applied security lab project

## Getting started

1. install vagrant [https://www.vagrantup.com/](https://www.vagrantup.com/)
2. go in the directory
3. start the client
  - `vagrant up client`
  - wait until started, provisioned and rebooted
  - login with user `vagrant` and password `vagrant`
4. start the webserver
  - `vagrant up webserver`
5. on the client go on to the url `http://webserver`, the apache2 default page should appear
6. suspend both VMs with `vagrant suspend`
