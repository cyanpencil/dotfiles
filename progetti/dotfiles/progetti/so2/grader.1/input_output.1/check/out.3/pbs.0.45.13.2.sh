#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=4:mpiruns=4
#PBS -A unutente
#PBS -l walltime=0:45:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/5/10/19/23/106.sh : -np 1 bash ./inp.3/0/1/5/10/19/100.sh : -np 1 bash ./inp.3/0/1/2/4/120.script : -np 1 bash ./inp.3/0/1/22/79.script 
