#!/bin/bash

set -ex

cd /backup

if [[ $(df | grep -c "/mnt/google") -eq 0 ]]; then
	echo "Mounting /mnt/google"
	google-drive-ocamlfuse /mnt/google
	mounted=1
fi

echo "Finding files too big..."
find / -size +100M 2>/dev/null | tr '[(\-\)\] ' '*' >/backup/files_too_large

echo "/dev/*" "/proc/*" "/tmp/*" "/sys/*" "/run/*" "/mnt/*" "/usr/*" "/lost+found/*" \
	 "/home/luca/.cache/*" "/backup/*" "/var/cache/pacman/pkg/*"  \
	 "/home/luca/.config/google-chrome" | tr ' ' '\n' >>/backup/files_too_large

newname="final_backup_$(date +'%Y-%m-%d').tar.gz"
sudo tar -I pigz -cvpf $newname --exclude-from=/backup/files_too_large /

# thinkpads t460s and x250 keys
gpg --recipient 4913CE9924BA9DB544714420488F3628BE88B032\
	--recipient 616608495AE48F7CD77010565323585557892953\
	--encrypt $newname 

rsync -ah ${newname}.gpg /mnt/google/${newname}.gpg --progress

# unmount garbage
if [[ $mounted -eq 1 ]]; then echo "Unmounting /mnt/google..."; sudo umount /mnt/google; fi
