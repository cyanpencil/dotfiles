#!/bin/bash

i3-msg "exec urxvt;"
i3-msg "split h; exec urxvt;"
sleep 0.25
i3-msg "split v; exec urxvt;"
