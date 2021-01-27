#!/bin/bash

total=""
for i in {2..0}; do 
	day=$(date +"%d_%m_%y" -d "$i days ago")
	total="$total /home/luca/stuff/gtd/$day.md"
done

for i in {1..2}; do 
	day=$(date +"%d_%m_%y" -d "$i days")
	total="$total /home/luca/stuff/gtd/$day.md"
done

vim -p $total -c tabnext -c tabnext
