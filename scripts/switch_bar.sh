#!/bin/bash
if [[ $# -ne 1 ]]; then
    vim -s ~/scripts/change_bar.keys  ~/.config/i3/config
    i3-msg restart
    exit 0
fi

if [[ $1 == "i3blocks" ]]; then
    vim -s ~/scripts/use_i3blocks.keys  ~/.config/i3/config
    i3-msg restart
    exit 0
fi

if [[ $1 == "i3status" ]]; then
    vim -s ~/scripts/use_i3status.keys  ~/.config/i3/config
    i3-msg restart
    exit 0
fi
