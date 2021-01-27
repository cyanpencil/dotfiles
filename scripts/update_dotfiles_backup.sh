#!/bin/bash

set -uxo pipefail

if [[ $EUID -ne 0 ]]; then echo "Please run as root"; exit 1; fi

syncpath="/home/luca/stuff/dotfiles"
homefiles=".bashrc .bash_profile .profile .vimrc .Xresources .xbindkeysrc .gitconfig .zshrc"
configdirs="i3 dunst ranger"
#homedirs="progetti scripts appunti calibre st"
homedirs="scripts"
others="/etc/X11/xinit/xinitrc /etc/udev/rules.d/powersave.rules\
	/etc/udev/rules.d/80-keyboard.rules /etc/X11/xinit/xserverrc\
	/etc/X11/xorg.conf.d/30-trackball-evdev.conf"


mkdir -p $syncpath
cd $syncpath

if [[ $? -ne 0 ]]; then 
    echo "Fallito cd $syncpath"
    exit 1
fi

for f in $homefiles; do
    echo "Copying $f"
    rsync -rav --exclude=".git" /home/luca/$f .
done

for f in $homedirs; do
    echo "Copying $f"
    rsync -rav --exclude=".git" /home/luca/$f .
done

for f in $configdirs; do
    echo "Copying $f"
    rsync -rav --exclude=".git" /home/luca/.config/$f .config
done

for f in $others; do
    echo "Copying $f"
    rsync -rav --exclude=".git" $f .
done



echo "Adding files to repository..."
git add ** .

echo "Committing..."
data=$(date +%Y/%m/%d\ %H:%M)
git commit -am "Auto backup $data"

echo "Pushing..."
git push

