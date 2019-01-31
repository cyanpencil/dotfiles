#!/bin/bash

if [[ $# -eq 0 ]]; then echo "Using default sleep time of 0.1"; fi
TIME=${1:-0.1}
fakeratio=$(bc <<< "1 / $TIME" )

line=$(cat /proc/net/dev | head -n 6 | tail -n 1)
read -a words <<< $line
total_download=${words[1]}
total_upload=${words[9]}

while [[ 1 ]]; do
	line=$(cat /proc/net/dev | head -n 6 | tail -n 1)
	read -a words <<< $line
	printf "Download:%7d KB/s\tUpload:  %7d KB/s\n" "$(( ((words[1] - total_download) * fakeratio) >> 10 ))" "$(( ((words[9] - total_upload) * fakeratio) >> 10 ))"

	sleep $TIME;
done
