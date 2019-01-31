#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=23:29:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/7/21/74.script : -np 1 bash ./inp.2/0/7/21/70.script : -np 1 bash ./inp.2/0/1/8/121.sh : -np 1 bash ./inp.2/0/1/8/12/131.script : -np 1 bash ./inp.2/0/1/8/117.sh : -np 1 bash ./inp.2/0/1/79.sh : -np 1 bash ./inp.2/0/1/3/83.sh : -np 1 bash ./inp.2/0/1/3/5/6/10/94.script 
