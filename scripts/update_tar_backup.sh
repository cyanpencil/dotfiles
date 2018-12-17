#!/bin/bash

cd /backup

echo -n "Password for lucadib:"
read -s password
echo


# First we check if pass is correct
# By uploading a dummy file to the server
echo "prova" > try_auth
success=$(curl -T try_auth "https://polybox.ethz.ch/remote.php/dav/files/lucadib/backup/try_auth" --user "lucadib:$password" 2>/dev/null)

if [[ $(echo $success | grep -c "incorrect") -ge 1 ]]; then
	echo "Incorrect password!"
	exit 1;
fi


find / -size +100M 2>/dev/null >/backup/files_too_large

newname="final_backup_$(date +'%Y-%m-%d').tar.gz"
sudo tar -I pigz -cvpf $newname --exclude={"/dev/*","/proc/*","/tmp/*","/sys/*","/run/*","/mnt/*","/lost+found/*","/home/luca/.cache/*","/backup/*","/var/cache/pacman/pkg/*"} --exclude-from=/backup/files_too_large /

curl -T $newname "https://polybox.ethz.ch/remote.php/dav/files/lucadib/backup/$newname" --user "lucadib:$password" >curl_result
cat curl_result

