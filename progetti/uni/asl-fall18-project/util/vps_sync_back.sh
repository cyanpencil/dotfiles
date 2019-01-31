#!/bin/bash

backup_folder=~/asl-fall18-project/archivio/scp_logs/$FOLDER/$(date +'%Y-%m-%d_%H:%M:%S')
mkdir -p $backup_folder
cp -p -r ~/asl-fall18-project/scp_logs/$FOLDER $backup_folder

find ~/asl-fall18-project/scp_logs/$FOLDER -name "*.log" -exec rm "{}" \;


rsync -ra www.cyanpencil.xyz:~/asl-fall18-project/scp_logs/ ~/asl-fall18-project/scp_logs/ --info=progress2 --filter=':- .gitignore'
