#!/bin/bash
xmodmap -e 'remove Lock = Caps_Lock'
xmodmap -e 'keysym Caps_Lock = Super_L'
xmodmap -e 'add Lock = Caps_Lock'

xcape -e 'Super_L=Escape' -t 300
