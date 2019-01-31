#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=8:45:31
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/3.script : -np 1 bash ./inp.2/0/2/9.script : -np 1 bash ./inp.2/0/2/23/29.script : -np 1 bash ./inp.2/0/2/20/20.sh : -np 1 bash ./inp.2/0/2/19/24.sh : -np 1 bash ./inp.2/0/2/13/39.script : -np 1 bash ./inp.2/0/2/13/18/52.sh : -np 1 bash ./inp.2/0/2/13/18/47.script 
