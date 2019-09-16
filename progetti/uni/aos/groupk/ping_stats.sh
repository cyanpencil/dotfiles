#!/bin/bash

echo -n "Input test name: "
read TEST_NAME

ITER=90
ITER_BURST=1000
IP=10.0.0.1

if [ -z "${TEST_NAME}" ]
then
    ITER=1
    ITER_BURST=100
fi

for SIZE in 10 20 50 100 200 500 1000 1472
do

    interval=0.$(printf "%03d" $(($SIZE/5+30)));
    if [ -z "${TEST_NAME}" ]
    then
        ping -c ${ITER} -s ${SIZE} -i ${interval} ${IP}  | head -n 2 | tail -n 1 | tr '\n' ' '
        echo -ne "===== "
    else
        ping -c ${ITER} -s ${SIZE} -i ${interval} ${IP} >  stats/ping_stats_${TEST_NAME}_${SIZE}.txt
    fi

    echo ${TEST_NAME}@${SIZE}, ${interval} DONE
done

for SIZE in 0 100 200
do
    ping -c${ITER_BURST} -s${SIZE} ${IP} -f > stats/ping_stats_${TEST_NAME}_burst_${SIZE}.txt
    echo BURST ${TEST_NAME}@${SIZE}, ${interval} DONE
done
