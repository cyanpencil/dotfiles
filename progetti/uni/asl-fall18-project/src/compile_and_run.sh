#!/bin/bash

recompile=false
javas=$(find . -iname "*.java")
for f in $javas; do
	if [[ $f -nt ${f%.java}.class ]]; then
		recompile=true;
	fi
done

compiled=0

if [[ $recompile == true ]]; then
	echo recompiling
	rm *.class
	javac -source 1.8 -target 1.8 RunMW.java
	compiled=$?
fi


if [ $# -lt 5 ]; then 
	args="-l localhost -p 11212 -t 4 -s true -m localhost:11211"; 
	echo "Not enough arguments given, using default $args"
else
	args=$@
fi

if [ $compiled == 0 ]; then
	java RunMW $args
fi
