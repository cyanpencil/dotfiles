#!/bin/bash
scrot '/home/luca/img/%Y-%m-%d_$wx$h.png' -a $(slop -f '%x,%y,%w,%h') -e 'xclip -selection clipboard -t "image/png" < $f'
