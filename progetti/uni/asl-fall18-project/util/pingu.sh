#!/bin/bash


SOURCE=$1
TARGET=$2

mkdir -p ssh_logs/pinged

ssh $SOURCE "ping -D $TARGET " | sed -u -e "1D;s/\[\([0-9\.]\+\).*time=\([0-9\.]*\).*/\1 \2/g" \
	> ssh_logs/pinged/pinged_${SOURCE}_${TARGET}



