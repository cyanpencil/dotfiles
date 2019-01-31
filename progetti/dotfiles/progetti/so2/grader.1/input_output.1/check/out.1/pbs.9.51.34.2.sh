#PBS -L select=0:ncpus=4:mpiprocs=4+1:ncpus=2:mpiruns=2
#PBS -A unutente
#PBS -l walltime=9:51:34
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/4/119.sh : -np 1 bash ./inp.1/0/1/2/13/139.script 
