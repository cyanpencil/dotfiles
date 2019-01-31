#PBS -L select=0:ncpus=4:mpiprocs=4+1:ncpus=3:mpiruns=3
#PBS -A unutente
#PBS -l walltime=9:50:6
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/4/122.script : -np 1 bash ./inp.1/0/1/2/13/19/145.script : -np 1 bash ./inp.1/0/1/2/13/131.script 
