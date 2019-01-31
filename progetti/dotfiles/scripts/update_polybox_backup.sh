#!/bin/bash

# mount the polybox on /mnt/sshdir
sudo mount -t davfs https://polybox.ethz.ch/remote.php/webdav /mnt/sshdir
sudo mkdir -p /mnt/sshdir/backup

# update local backup on /backup
sudo rsync -aHAXS / /backup --exclude={"/dev/*","/proc/*","/tmp/*","/sys/*","/run/*","/mnt/*","/lost+found/*","/home/luca/.cache/*","/backup/*","/var/cache/pacman/pkg/*"} --max-size=100MB -v --inplace -W --delete

# compress backup (use pigz for parallel compression)
sudo tar -I pigz -cpvf ~/final_backup /backup/

sudo chown luca ~/final_backup

newname="~/final_backup_$(date +'%Y-%m-%d').tar"
sudo mv ~/final_backup $newname

sudo cp $newname /mnt/sshdir/backup/

