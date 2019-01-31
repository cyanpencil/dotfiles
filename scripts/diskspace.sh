#!/bin/bash

dim="\e[0;2m"
clear="\e[0m"

mountpoints="/ "$(ls -d /mnt/*)
barWidth=40

result=$(df -h)
count=0

IFS=$'\n';
for line in $result; do
	IFS=$' \t\n'
	if [[ $count -eq 0 ]]; then echo "$line"; count=1; fi
	read -a a <<< $line
	for point in ${mountpoints}; do
		if [[ ${a[${#a[@]}-1]} != $point ]]; then continue; fi

		echo "${line}"
		usagePercent=$(echo "$line"|tail -n1|awk '{print $5;}'|sed 's/%//')
		usedBarWidth=$(bc <<< "($usagePercent*$barWidth) / 100")
		color="\e[32;1m"
		if [[ "${usagePercent}" -ge 80 ]]; then color="\e[31;1m"; fi
		barContent=$(printf "%${barWidth}s" | tr ' ' '=')
		first=${barContent:0:$usedBarWidth}
		second=${barContent:$usedBarWidth:$barWidth}
		echo -e "[$color$first$dim$second$clear]"
	done
done

