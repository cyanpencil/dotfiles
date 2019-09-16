#!/bin/bash

for x in {0..4}; do 
	for i in {30..37} {90..97}; do 
		for a in {40..47} {100..107}; do 
			echo -ne "\e[$x;$i;$a""m\\\e[$x;$i;$a""m\e[0;37;40m "
		done
		echo
	done
done
echo ""
