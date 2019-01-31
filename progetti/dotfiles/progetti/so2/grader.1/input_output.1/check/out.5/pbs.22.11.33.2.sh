#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=6:mpiruns=6
#PBS -A unutente
#PBS -l walltime=22:11:33
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/15/17/18/152.script : -np 1 bash ./inp.5/0/1/2/3/15/130.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/98.script : -np 1 bash ./inp.5/0/1/2/3/10/11/13/19/121.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/13/110.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/13/103.script 
