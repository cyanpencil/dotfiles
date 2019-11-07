#!/bin/bash
syncpath="/home/luca/dotfiles"
homefiles=".bashrc .bash_profile .vimrc .Xresources .xbindkeysrc useful .fzf.bash .inputrc .gitconfig"
configdirs="i3 dunst fish"
homedirs="progetti scripts appunti calibre st"
others="/etc/X11/xinit/xinitrc /etc/udev/rules.d/powersave.rules\
	/etc/udev/rules.d/80-keyboard.rules /etc/X11/xinit/xserverrc"


cd $syncpath

if [[ $? -ne 0 ]]; then 
    echo "Fallito cd $syncpath"
    exit 1
fi

for f in $homefiles; do
    echo "Copying $f"
    rsync -rav --exclude=".git" ~/$f .
    #cp ~/$f .
done

for f in $homedirs; do
    echo "Copying $f"
    rsync -rav --exclude=".git" ~/$f .
    #cp -r ~/$f .
done

for f in $configdirs; do
    echo "Copying $f"
    rsync -rav --exclude=".git" ~/.config/$f .config
    #cp -r ~/.config/$f .config
done

for f in $others; do
    echo "Copying $f"
    rsync -rav --exclude=".git" $f .
    #cp $f .
done



echo "Adding files to repository..."
git add ** .

echo "Committing..."
data=$(date +%Y/%m/%d\ %H:%M)
git commit -am "Auto backup $data"

echo "Pushing..."
git push
