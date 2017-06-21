#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=6:mpiruns=6
#PBS -A unutente
#PBS -l walltime=23:24:12
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/15/17/18/150.script : -np 1 bash ./inp.5/0/1/2/3/15/17/139.sh : -np 1 bash ./inp.5/0/1/2/3/15/135.script : -np 1 bash ./inp.5/0/1/2/3/10/14/21/128.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/94.script : -np 1 bash ./inp.5/0/1/2/3/10/11/13/19/117.sh 
