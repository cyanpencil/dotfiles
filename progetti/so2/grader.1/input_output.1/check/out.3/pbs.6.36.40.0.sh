#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=6:36:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/2.script : -np 1 bash ./inp.3/0/6/35.script : -np 1 bash ./inp.3/0/3/16/31.script : -np 1 bash ./inp.3/0/3/16/27.script : -np 1 bash ./inp.3/0/17/13.sh : -np 1 bash ./inp.3/0/6/9/49.script : -np 1 bash ./inp.3/0/6/9/11/53.sh : -np 1 bash ./inp.3/0/6/8/13/68.sh 
