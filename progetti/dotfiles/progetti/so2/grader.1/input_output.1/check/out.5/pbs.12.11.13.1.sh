#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=12:11:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/10/89.sh : -np 1 bash ./inp.5/0/1/2/3/10/85.sh : -np 1 bash ./inp.5/0/1/20.script : -np 1 bash ./inp.5/0/1/12/27/38.script : -np 1 bash ./inp.5/0/10.script : -np 1 bash ./inp.5/0/1/2/4/6/8/169.script : -np 1 bash ./inp.5/0/1/2/4/6/164.script : -np 1 bash ./inp.5/0/1/2/4/6/160.script 
