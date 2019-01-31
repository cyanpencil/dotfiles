#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=7:mpiruns=7
#PBS -A unutente
#PBS -l walltime=9:42:35
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/7/12/132.sh : -np 1 bash ./inp.3/0/1/5/94.sh : -np 1 bash ./inp.3/0/1/5/90.sh : -np 1 bash ./inp.3/0/1/5/10/19/23/111.script : -np 1 bash ./inp.3/0/1/5/10/19/102.sh : -np 1 bash ./inp.3/0/1/22/81.script : -np 1 bash ./inp.3/0/1/2/117.script 
