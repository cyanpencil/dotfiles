#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=4:mpiruns=4
#PBS -A unutente
#PBS -l walltime=8:45:31
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/1/3/5/6/10/17/28/102.script : -np 1 bash ./inp.2/0/1/3/4/111.script : -np 1 bash ./inp.2/0/1/3/15/87.script : -np 1 bash ./inp.2/0/1/24/134.script 
