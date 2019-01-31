#!/bin/bash

if [ $# -lt 5 ]; then 
	args="-l localhost -p 11212 -t 4 -s true -m localhost:11211"; 
	echo "Not enough arguments given, using default $args"
else
	args=$@
fi

java RunMW $args
