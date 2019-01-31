#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=2:mpiruns=2
#PBS -A unutente
#PBS -l walltime=6:36:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/22/86.sh : -np 1 bash ./inp.3/0/1/22/78.sh 
