#!/bin/bash

echo "Plumbing!"

if [[ -f $1 ]]; then

	case $1 in
		*.pdf)
			zathura $1
		;;
		*.cpp|*.c|*.py|*.java|*.js|*.txt|*.html|*.h|*.sh|*.tex)
			st -e vim $1
		;;
		*.png|*.jpg|*.jpeg|*.gif)
			#feh -^ floating $1
			sxiv -N floating $1
		;;
		*)
			st -e vim $1
		;;
	esac
elif [[ -d $1 ]]; then
	st -n flying_ranger -t flying_ranger -e ranger $1
else 

	case $1 in
		http*)
			firefox $1
		;;
	esac

	if [[ $1 =~ 0x[0-9]+ ]]; then 
		notify-send -u low "RAX2" "int: $(rax2 $1)\nchr: \"$(rax2 -s $1)\"\nbin: $(rax2 Bx${1#0x})"
	elif [[ $1 =~ [0-9]+ ]]; then
		notify-send -u low "RAX2" "$(rax2 -r $1 | head -n 6)\n $(printf '\u2063') \n"
	fi
	#elif [[ $1 =~ [0-9a-fA-F]+ ]]; then


fi
