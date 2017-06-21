#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=1:mpiruns=1
#PBS -A unutente
#PBS -l walltime=12:11:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/10/11/13/107.script 
