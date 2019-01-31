#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=22:11:33
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/10/86.script : -np 1 bash ./inp.5/0/1/21.sh : -np 1 bash ./inp.5/0/11.sh : -np 1 bash ./inp.5/0/1/12/27/34.script : -np 1 bash ./inp.5/0/1/2/4/6/8/173.script : -np 1 bash ./inp.5/0/1/2/4/6/25/165.script : -np 1 bash ./inp.5/0/1/2/4/157.script : -np 1 bash ./inp.5/0/1/2/3/15/17/22/141.script 
