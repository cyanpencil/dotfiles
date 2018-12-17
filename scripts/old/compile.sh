#!/bin/bash


function cpp {
	echo "wow"
}

function python {
	echo "woah"
}



if [[ $# -eq 0 ]]; then echo "Not enough arguments given!"; exit 1; fi

case "$1" in
	*.c)	cpp ;;
	*.py)	python ;;
	*.tex)	latex ;;
esac



