#!/bin/bash

TEST_NO=$1
FOLDER="middleware_${TEST_NO:=1}"

mkdir -p ssh_logs/$FOLDER
mkdir -p scp_logs/$FOLDER

SSH_USER="osboxes"
MCACHE_PORT="11213"
MWARE_PORT="11213"

log () {
	pink="\x1b[35m"
	nocolor="\x1b[0m"
	yellow="\x1b[33m"
	echo -e $(date +"$pink[%H:%M:%S]$nocolor")"$yellow[${rep:-?}][${ratio:-???}]$nocolor" $1
}



if [[ $TEST_NO -eq 1 ]]; then
	MTIER_THREADS=2
	#MTIER_ADDR=(
		#"192.168.56.102"
		#"192.168.56.103"
		#)
	#MWARE_ADDR="192.168.56.104"
	#MWARE_PRIV_IPS="192.168.56.104"

	#MCACHE_ADDR=( "192.168.56.105" )
	#MCACHE_PRIV_IPS=( "192.168.56.105")

	MTIER_ADDR=(
		"storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com"
		)

	MWARE_ADDR=("storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com")
	MWARE_PRIV_IPS=("10.0.0.9")

	MCACHE_ADDR=( "storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com" )
	MCACHE_PRIV_IPS=( "10.0.0.5")
else 
	MTIER_THREADS=1
	#MTIER_ADDR=(
		#"192.168.56.102"
		#)
	#MWARE_ADDR=(
		#"192.168.56.102"
		#"192.168.56.103"
		#)
	#MWARE_PRIV_IPS=(
		#"192.168.56.102"
		#"192.168.56.103"
		#)
	#MCACHE_ADDR=( "192.168.56.105" )
	#MCACHE_PRIV_IPS=( "192.168.56.105")

	MTIER_ADDR=(
		"storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com"
		)

	MWARE_ADDR=("storewz2nt2k6rryc2sshpublicip4.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip5.westeurope.cloudapp.azure.com"
		)
	MWARE_PRIV_IPS=("10.0.0.9" "10.0.0.4")

	MCACHE_ADDR=( "storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com" )
	MCACHE_PRIV_IPS=( "10.0.0.5")
fi


( cd src; ./compile.sh )
if [[ $? -ne 0 ]]; then exit $?; fi



for server in "${MCACHE_ADDR[@]}"; do
	ssh "${SSH_USER}@${server}" "killall memtier_benchmark java javac memcached" &>/dev/null
	log "[+] launching memcached daemon $server"
	ssh "${SSH_USER}@${server}" "memcached -p $MCACHE_PORT -d"
done

for addr in "${MTIER_ADDR[@]}"; do
	ssh "${SSH_USER}@${addr}" "
	rm -rf logs/$FOLDER/*
	killall memtier_benchmark java javac memcached
	" &>/dev/null
done

MCACHE_IP_PORT=""
for server in "${MCACHE_PRIV_IPS[@]}"; do
	MCACHE_IP_PORT=$MCACHE_IP_PORT" $server:$MCACHE_PORT"
done


backup_folder=archivio/scp_logs/$FOLDER/$(date +'%Y-%m-%d_%H:%M:%S')
mkdir -p $backup_folder
cp -p -r scp_logs/$FOLDER $backup_folder

find scp_logs/$FOLDER -name "*.log" -exec rm "{}" \;





#MTIER_CLIENTS=(1 2 4 8 16 32)
#WORKER_THREADS=(8 16 32 64)
#TIME=60
#RATIO=("1:0" "0:1")
#DEFAULT_ARGS="--data-size=4096 --protocol=memcache_text --expiry-range=9999-10000 --key-maximum=1000 --hide-histogram"
#REPS=3


MTIER_CLIENTS=(1 2 4 8 16 32)
WORKER_THREADS=(8 16 32 64)
TIME=60
RATIO=("1:0" "0:1")
DEFAULT_ARGS="--data-size=4096 --protocol=memcache_text --expiry-range=9999-10000 --key-maximum=1000 --hide-histogram"
REPS=3

#MTIER_CLIENTS=(32)
#WORKER_THREADS=(64)
#TIME=30
#RATIO=("1:0")
#DEFAULT_ARGS="--data-size=4096 --protocol=memcache_text --expiry-range=9999-10000 --key-maximum=1000 --hide-histogram"
#REPS=1

total=$(bc <<< "${#MTIER_CLIENTS[@]} * ${#WORKER_THREADS[@]} * ${#RATIO[@]} * $REPS")
counter=1

echo -e "\e[1mStarting test with $total batches and configuration:\e[0m
TIME:$TIME\nREPS:$REPS\nRATIO:${RATIO[@]}\nCLIENTS:${MTIER_CLIENTS[@]}\nWORKERS:${WORKER_THREADS[@]}"


for rep in `seq 1 $REPS`; do
for ratio in "${RATIO[@]}"; do
for workers in "${WORKER_THREADS[@]}"; do
for no_clients in "${MTIER_CLIENTS[@]}"; do

	time_start=$(date +%s)

	for ware in "${MWARE_ADDR[@]}"; do
		MWARE_ARGS="-l 0 -p $MWARE_PORT -t $workers -s 1 -m $MCACHE_IP_PORT"
		log "Starting middleware at $ware with args $MWARE_ARGS"
		scp -pr src "${SSH_USER}@${ware}":~ &>/dev/null
		ssh "${SSH_USER}@${ware}" "
		killall -INT compile_and_run.sh memtier_benchmark java javac memcached
		rm ~/middleware_log.log
		cd src/
		./compile_and_run.sh $MWARE_ARGS
		" &>>ssh_logs/$FOLDER/sshlog.log &
		ssh "${SSH_USER}@${ware}" "while [[ \$(ss -tuna | grep -c 'LISTEN.*$MCACHE_PORT') -lt 1 ]]; do $(typeset -f);log '[!] Waiting for javac...'; sleep 1;done"
	done


	waitlist=""
	REQTYPE="gets"
	if [[ $ratio == "1:0" ]]; then REQTYPE="sets"; fi
	JSONFILE="${REQTYPE}_${no_clients}_clients_rep_${rep}_workers_${workers}.log"

	for maddr in "${MTIER_ADDR[@]}"; do
		s=0
		for server in "${MWARE_PRIV_IPS[@]}"; do
			ARGS="--server=$server --port=$MCACHE_PORT  --clients=$no_clients --threads=$MTIER_THREADS\
				--test-time=$TIME --ratio=$ratio $DEFAULT_ARGS --json-out-file=logs/$FOLDER/$s/$JSONFILE"

			log "[+] launching memtier from $maddr with args $ARGS"

			ssh  "${SSH_USER}@${maddr}" "
			mkdir -p logs/$FOLDER/$s
			memtier_benchmark-master/memtier_benchmark $ARGS " &>>ssh_logs/$FOLDER/sshlog.log &
			waitlist="$waitlist $!"
			s=$((s + 1))
		done
	done

	log "waiting for $waitlist..."
	wait $waitlist
	sleep 1

	log "Copying logs..."

	for (( s=0; s<${#MWARE_ADDR[@]}; s++ )); do
		mkdir -p scp_logs/$FOLDER/${MWARE_ADDR[s]}
		scp -p ${SSH_USER}@${MWARE_ADDR[s]}:/home/osboxes/middleware_log.log scp_logs/$FOLDER/${MWARE_ADDR[s]}/$JSONFILE &>/dev/null
		ssh ${SSH_USER}@${MWARE_ADDR[s]}  "killall -INT java
		while [[ \$(pgrep java | wc -l) -gt 0 ]]; do $(typeset -f);log '[!] Waiting for shutdown... '; sleep 1;done"

		for addr in "${MTIER_ADDR[@]}"; do
			mkdir -p scp_logs/$FOLDER/${addr}_${s}
			scp -p -r ${SSH_USER}@${addr}:/home/osboxes/logs/$FOLDER/$s/* scp_logs/$FOLDER/${addr}_${s} &>/dev/null
		done
	done

	time_end=$(date +%s)
	secs=$((time_end-time_start))
	secs=$((secs*(total-counter)))

	left=$(printf '%dh:%dm:%ds\n' $(($secs/3600)) $(($secs%3600/60)) $(($secs%60)))
	log "\e[1mFinished batch ${counter}/${total}!\e[0m (est. time left: $left)"
	counter=$((counter+1))
done
done
done
done
