#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=15:42:49
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/10/82.script : -np 1 bash ./inp.5/0/1/12/27/35.sh : -np 1 bash ./inp.5/0/1/2/4/6/8/174.sh : -np 1 bash ./inp.5/0/1/2/4/6/25/166.sh : -np 1 bash ./inp.5/0/1/2/4/6/162.script : -np 1 bash ./inp.5/0/1/2/4/6/158.script : -np 1 bash ./inp.5/0/1/2/3/15/17/18/148.script : -np 1 bash ./inp.5/0/1/2/3/15/17/138.script 
