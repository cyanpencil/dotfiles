#!/bin/bash

if [[ $# -eq 0 ]]; then echo "Using default sleep time of 0.1"; fi
TIME=${1:-0.1}
fakeratio=$(bc <<< "1 / $TIME" )
while [[ 1 ]]; do
	line=$(cat /proc/net/dev | grep wl)
	read -a words <<< $line
	printf "Download:%5d KB/s\tUpload:  %5d KB/s\n" "$(( ((words[1] - prev_d) * fakeratio) >> 10 ))" "$(( ((words[9] - prev_u) * fakeratio) >> 10 ))"

	prev_d=${words[1]}
	prev_u=${words[9]};
	sleep $TIME;
done
