#!/bin/bash

VM_ADDR="localhost:2222"
IFS=: read VM_IP VM_PORT <<< $VM_ADDR

MTIER_PORT=11212

MCACHE_ADDR="localhost:11213"
IFS=: read MCACHE_IP MCACHE_PORT <<< "$MCACHE_ADDR"
NO_MCACHE=2

MWARE_FLAGS="-l localhost -p 11212 -t 4 -s true -m "

log () {
	pink="\x1b[35m"
	nocolor="\x1b[0m"
	echo -e $(date +"$pink[%d/%m/%Y %H:%M:%s]$nocolor") $1
}

ssh $VM_IP -p $VM_PORT "sudo killall memtier_benchmark java javac memcached"

MCACHE_PORTS=""
for i in `seq 0 $(echo $NO_MCACHE-1 | bc)`; do
	THIS_PORT=$(echo $MCACHE_PORT + $i | bc)
	MCACHE_PORTS=$MCACHE_PORTS" $MCACHE_IP:$THIS_PORT"
	ssh $VM_IP -p $VM_PORT "tmux send -t mcache$i 'memcached -p $THIS_PORT' ENTER"
	log "[+] Started memcache server at port $THIS_PORT"
done

MWARE_FLAGS=$MWARE_FLAGS$MCACHE_PORTS
ssh $VM_IP -p $VM_PORT "tmux send -t mware './compile_and_run.sh $MWARE_FLAGS' ENTER"
log "[+] Started middleware with flags $MWARE_FLAGS" 

ssh $VM_IP -p $VM_PORT "while [[ \$(ss -tuna | grep -c 'LISTEN.*$MTIER_PORT') -lt 1 ]]; do $(typeset -f);log '[!] Waiting for javac...'; sleep 1;done"


ssh $VM_IP -p $VM_PORT "tmux send -t mtier './prova.sh' ENTER"

log "[+] Started memtier"


