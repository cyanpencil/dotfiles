#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=8:45:31
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/7/25/65.sh : -np 1 bash ./inp.2/0/7/21/73.sh : -np 1 bash ./inp.2/0/1/8/124.script : -np 1 bash ./inp.2/0/1/8/120.script : -np 1 bash ./inp.2/0/1/78.script : -np 1 bash ./inp.2/0/1/3/82.script : -np 1 bash ./inp.2/0/1/3/5/6/10/93.sh : -np 1 bash ./inp.2/0/1/3/5/6/10/17/98.script 
