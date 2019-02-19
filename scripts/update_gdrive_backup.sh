#!/bin/bash

cd /backup

if [[ $(df | grep -c "/mnt/google") -eq 0 ]]; then
	echo "Mounting /mnt/google"
	google-drive-ocamlfuse /mnt/google
	mounted=1
fi

find / -size +100M 2>/dev/null | tr '[(\-\)\] ' '*' >/backup/files_too_large

echo "/dev/*" "/proc/*" "/tmp/*" "/sys/*" "/run/*" "/mnt/*" "/lost+found/*" \
	 "/home/luca/.cache/*" "/backup/*" "/var/cache/pacman/pkg/*"  \
	 "/home/luca/.config/google-chrome" | tr ' ' '\n' >>/backup/files_too_large

newname="final_backup_$(date +'%Y-%m-%d').tar.gz"
sudo tar -I pigz -cvpf $newname --exclude-from=/backup/files_too_large /

rsync -ah $newname /mnt/google/$newname --progress

# unmount garbage
if [[ $mounted -eq 1 ]]; then echo "Unmounting /mnt/google..."; sudo umount /mnt/google; fi
