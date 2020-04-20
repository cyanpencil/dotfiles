#!/usr/bin/env bash

mysqldump -uroot -proot --all-databases | gzip > /home/vagrant/database_backup.sql.gz
scp /home/vagrant/database_backup.sql.gz database@backup:/home/database/
