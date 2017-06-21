#!/usr/bin/env bash

################################
# Shows info about the RAM
#
# Dependencies:
# - [notify-send]
#
# @return {Number(%)}: RAM usage
################################

full=""
short=""
status=0

exec 6< /proc/meminfo
read a total b <&6
read a free b <&6
read a b c <&6
read a buffer b <&6
read a cached b <&6

MEM=$(echo "100 * ($total - ($free + $buffer + $cached)) / $total" | bc)

full="$MEM%"
short=$full

#echo $MEM
#echo $total , $free , $buffer, $cached

if [ $MEM -ge 80 ]; then
    status=33
fi

case $BLOCK_BUTTON in
    # click: show packages
    1 | 2 | 3) 
    n=16
    summary=$(ps -eo pmem,pcpu,pid,psr,comm --sort=-pmem | head -$n)
    notify-send "Top $n RAM-eaters" "$summary"
    ;;
esac

echo $full
#echo $short
exit $status
