#!/bin/bash

TEST_NO=$1
FOLDER="baseline_${TEST_NO:=1}"

mkdir -p ssh_logs/$FOLDER

SSH_USER="osboxes"
MCACHE_PORT="11213"

log () {
	pink="\x1b[35m"
	nocolor="\x1b[0m"
	yellow="\x1b[33m"
	echo -e $(date +"$pink[%H:%M:%S]$nocolor")"$yellow[${rep:-?}][${ratio:-???}]$nocolor" $1
}



if [[ $TEST_NO -eq 1 ]]; then
	#MTIER_ADDR=(
		#"192.168.56.102"
		#"192.168.56.103"
		#"192.168.56.104"
		#)

	#MCACHE_ADDR=( "192.168.56.105" )
	#MCACHE_PRIV_IPS=( "192.168.56.104")
	MTIER_THREADS=2
	MTIER_ADDR=(
		"storewz2nt2k6rryc2sshpublicip1.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip2.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com"
	)
	MCACHE_ADDR="storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com"
	MCACHE_PRIV_IPS=("10.0.0.5")
else 
	#MTIER_ADDR=( "192.168.56.102")
	#MCACHE_ADDR=(
		#"192.168.56.104"
		#"192.168.56.105" 
		#)
	#MCACHE_PRIV_IPS=(
		#"192.168.56.105"
		#"192.168.56.104"
		#)
	MTIER_THREADS=1

	MTIER_ADDR=(
		"storewz2nt2k6rryc2sshpublicip3.westeurope.cloudapp.azure.com"
		)
	MCACHE_ADDR=("storewz2nt2k6rryc2sshpublicip6.westeurope.cloudapp.azure.com"
		"storewz2nt2k6rryc2sshpublicip7.westeurope.cloudapp.azure.com"
		)
	MCACHE_PRIV_IPS=("10.0.0.5" "10.0.0.6")
fi



for server in "${MCACHE_ADDR[@]}"; do
	ssh "${SSH_USER}@${server}" "killall memtier_benchmark java javac memcached" &>/dev/null
	log "[+] launching memcached daemon $server"
	ssh "${SSH_USER}@${server}" "memcached -p $MCACHE_PORT -d"
done

for addr in "${MTIER_ADDR[@]}"; do
	ssh "${SSH_USER}@${addr}" "rm -rf logs/$FOLDER/*"
done


#MTIER_CLIENTS=(1 2 4 8 12 16 20 24 28 32)
MTIER_CLIENTS=(1 2 4 8 16 32)
TIME=60
RATIO=("1:0" "0:1")
#RATIO=("1:0")
DEFAULT_ARGS="--data-size=4096 --protocol=memcache_text --expiry-range=9999-10000 --key-maximum=1000 --hide-histogram"
REPS=3


for rep in `seq 1 $REPS`; do
for ratio in "${RATIO[@]}"; do
for no_clients in "${MTIER_CLIENTS[@]}"; do
	waitlist=""
	for maddr in "${MTIER_ADDR[@]}"; do
		REQTYPE="gets"
		if [[ $ratio == "1:0" ]]; then REQTYPE="sets"; fi

		JSONFILE="${REQTYPE}_${no_clients}_clients_rep_${rep}.log"
		s=0
		for server in "${MCACHE_PRIV_IPS[@]}"; do
			ARGS="--server=$server --port=$MCACHE_PORT  --clients=$no_clients --threads=$MTIER_THREADS\
				--test-time=$TIME --ratio=$ratio $DEFAULT_ARGS --json-out-file=logs/$FOLDER/$s/$JSONFILE"

			log "[+] [Rep: ${rep}] launching memtier from $maddr with args $ARGS"


			ssh  "${SSH_USER}@${maddr}" "
			mkdir -p logs/$FOLDER/$s
			memtier_benchmark-master/memtier_benchmark $ARGS " &>>ssh_logs/$FOLDER/sshlog.log &
			waitlist="$waitlist $!"
			s=$((s + 1))
		done
	done
	log "\e[1mFinished batch, waiting for $waitlist...\e[0m"
	wait $waitlist
	sleep 1
done
done
done


backup_folder=archivio/scp_logs/$FOLDER/$(date +'%Y-%m-%d_%H:%M:%S')
mkdir -p $backup_folder
cp -p -r scp_logs/$FOLDER $backup_folder


find scp_logs/$FOLDER -name "*.log" -exec rm "{}" \;

for (( s=0; s<${#MCACHE_ADDR[@]}; s++ )); do
	for addr in "${MTIER_ADDR[@]}"; do
		mkdir -p scp_logs/$FOLDER/${addr}_${s}
		scp -p -r ${SSH_USER}@${addr}:/home/osboxes/logs/$FOLDER/$s/* scp_logs/$FOLDER/${addr}_${s}
	done
done

