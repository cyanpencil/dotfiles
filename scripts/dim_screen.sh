#!/bin/bash
sleep 0.5
xbacklight -get > /tmp/oldlockbacklight
xbacklight -set 10
while [[ $(xprintidle) -ge 500 ]]; do
	sleep 0.1;
done
xbacklight -set $(cat /tmp/oldlockbacklight)
