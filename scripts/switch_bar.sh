#!/bin/bash
if [[ $# -ne 1 ]]; then
    vim -s /home/luca/scripts/change_bar.keys  /home/luca/.config/i3/config
    i3-msg reload
    exit 0
fi

if [[ $1 == "i3blocks" ]]; then
    vim -s /home/luca/scripts/use_i3blocks.keys  /home/luca/.config/i3/config
    i3-msg reload
    exit 0
fi

if [[ $1 == "i3status" ]]; then
    vim -s /home/luca/scripts/use_i3status.keys  /home/luca/.config/i3/config
    i3-msg reload
    exit 0
fi
