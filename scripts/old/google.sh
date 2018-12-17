#!/bin/bash
str=
a=0
for i in "$@"; do 
    a=$[a + 1]
    #a=$(echo $a + 1 | bc)
    #a=$(bc <<< "$a + 1")
    if [[ $a == 1 ]]; then
        str="$str$i"
    else
        str="$str+$i" 
    fi
done
echo $str
firefox -new-window -url "http://www.google.com/search?q=""$str"
i3-msg workspace "1 "
