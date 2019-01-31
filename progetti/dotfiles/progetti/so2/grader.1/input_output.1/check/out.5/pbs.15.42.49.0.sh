#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=15:42:49
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/8.script : -np 1 bash ./inp.5/0/17.sh : -np 1 bash ./inp.5/0/1/5/7/56.script : -np 1 bash ./inp.5/0/1/5/7/51.sh : -np 1 bash ./inp.5/0/12.script : -np 1 bash ./inp.5/0/1/2/74.script : -np 1 bash ./inp.5/0/1/23/69.script : -np 1 bash ./inp.5/0/1/23/65.script 
