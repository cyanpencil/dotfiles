#!/bin/bash

recompile=false
javas=$(find . -iname "*.java")
for f in $javas; do
	if [[ $f -nt ${f%.java}.class ]]; then
		recompile=true;
	fi
done

if [[ $recompile == true ]]; then
	echo recompiling
	rm *.class
	javac -target 1.8 -source 1.8 RunMW.java
	exit $?
fi

exit 0
