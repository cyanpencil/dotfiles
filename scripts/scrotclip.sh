#!/bin/bash
scrot '/home/luca/img/%Y-%m-%d_$wx$h.png' -s -e 'xclip -selection clipboard -t "image/png" < $f'
