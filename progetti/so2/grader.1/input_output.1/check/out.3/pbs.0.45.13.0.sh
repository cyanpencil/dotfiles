#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=0:45:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/3/26/23.script : -np 1 bash ./inp.3/0/3/16/28.sh : -np 1 bash ./inp.3/0/3/16/18/32.script : -np 1 bash ./inp.3/0/6/9/50.sh : -np 1 bash ./inp.3/0/6/8/60.sh : -np 1 bash ./inp.3/0/6/8/13/15/70.sh : -np 1 bash ./inp.3/0/6/20/42.sh : -np 1 bash ./inp.3/0/29/166.script 
