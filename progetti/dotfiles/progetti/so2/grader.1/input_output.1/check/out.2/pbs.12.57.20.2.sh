#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=3:mpiruns=3
#PBS -A unutente
#PBS -l walltime=12:57:20
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/1/3/5/6/10/17/101.sh : -np 1 bash ./inp.2/0/1/3/5/16/27/110.script : -np 1 bash ./inp.2/0/1/3/4/9/114.script 
