#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=9:42:35
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/5.sh : -np 1 bash ./inp.3/0/3/19.script : -np 1 bash ./inp.3/0/3/16/29.script : -np 1 bash ./inp.3/0/3/16/25.script : -np 1 bash ./inp.3/0/3/16/18/33.sh : -np 1 bash ./inp.3/0/17/15.sh : -np 1 bash ./inp.3/0/11.sh : -np 1 bash ./inp.3/0/6/9/51.script 
