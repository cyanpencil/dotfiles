#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=16:12:23
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/6.script : -np 1 bash ./inp.3/1.sh : -np 1 bash ./inp.3/0/6/20/38.sh : -np 1 bash ./inp.3/0/3/21.script : -np 1 bash ./inp.3/0/3/16/30.sh : -np 1 bash ./inp.3/0/3/16/26.sh : -np 1 bash ./inp.3/0/3/16/18/34.script : -np 1 bash ./inp.3/0/17/16.script 
