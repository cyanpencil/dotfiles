#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=15:56:51
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/6.script : -np 1 bash ./inp.4/0/3/26.sh : -np 1 bash ./inp.4/0/3/16/26/70.script : -np 1 bash ./inp.4/0/3/16/19/52.script : -np 1 bash ./inp.4/0/3/12/35.script : -np 1 bash ./inp.4/0/3/12/31.script : -np 1 bash ./inp.4/0/3/10/20/46.sh : -np 1 bash ./inp.4/0/2.script : -np 1 bash ./inp.4/0/1/81.script : -np 1 bash ./inp.4/0/1/5/90.script : -np 1 bash ./inp.4/0/1/5/8/94.sh : -np 1 bash ./inp.4/0/1/5/86.script 
