#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=3:mpiruns=3
#PBS -A unutente
#PBS -l walltime=23:29:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/1/3/5/6/10/17/99.sh : -np 1 bash ./inp.2/0/1/3/5/6/10/11/108.sh : -np 1 bash ./inp.2/0/1/24/137.sh 
