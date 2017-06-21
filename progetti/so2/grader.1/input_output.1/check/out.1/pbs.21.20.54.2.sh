#PBS -L select=0:ncpus=4:mpiprocs=4+1:ncpus=2:mpiruns=2
#PBS -A unutente
#PBS -l walltime=21:20:54
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/13/19/146.sh : -np 1 bash ./inp.1/0/1/2/13/132.sh 
