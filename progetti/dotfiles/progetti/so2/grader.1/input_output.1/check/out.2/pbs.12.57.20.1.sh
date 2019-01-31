#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=12:57:20
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/7/21/68.script : -np 1 bash ./inp.2/0/1/8/123.sh : -np 1 bash ./inp.2/0/1/8/12/129.script : -np 1 bash ./inp.2/0/1/8/119.sh : -np 1 bash ./inp.2/0/1/3/86.script : -np 1 bash ./inp.2/0/1/3/5/6/10/92.script : -np 1 bash ./inp.2/0/1/3/5/6/10/17/96.script : -np 1 bash ./inp.2/0/1/3/5/6/10/17/28/105.sh 
