#!/bin/bash
syncpath="/home/luca/sync/dotfiles"
homefiles=".vimrc .Xresources .xbindkeysrc useful .fzf.bash .inputrc"
configdirs="i3 dunst fish"
homedirs="progetti scripts appunti calibre"
others="/etc/X11/xinit/xinitrc"


cd $syncpath

if [[ $? -ne 0 ]]; then 
    echo "Fallito cd $syncpath"
    exit 1
fi

for f in $homefiles; do
    echo "Copying $f"
    cp ~/$f .
done

for f in $homedirs; do
    echo "Copying $f"
    cp -r ~/$f .
done

for f in $configdirs; do
    echo "Copying $f"
    cp -r ~/.config/$f .config
done

for f in $others; do
    echo "Copying $f"
    cp $f .
done



echo "Adding files to repository..."
git add ** .

echo "Committing..."
data=$(date +%Y/%m/%d\ %H:%M)
git commit -am "Auto backup $data"

echo "Pushing..."
git push

