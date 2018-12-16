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
		*.png|*.jpg|*.gif)
			feh -^ floating $1
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
			google-chrome-stable $1
		;;
	esac

fi
