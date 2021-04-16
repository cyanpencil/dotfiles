#!/bin/bash

scrot '/home/luca/img/%Y-%m-%d_$wx$h.png' -a $(slop -f '%x,%y,%w,%h') -e 'xclip -selection clipboard -t "image/png" < $f'
if [[ $1 == "--paste" ]]; then
	cd /home/luca/img/
	fname=$(ls -1rt *.png | tail -n 1)
	cp /home/luca/img/$fname /home/luca/vimwiki/assets/
	echo "![](assets/$fname)" | xclip -selection primary
fi
