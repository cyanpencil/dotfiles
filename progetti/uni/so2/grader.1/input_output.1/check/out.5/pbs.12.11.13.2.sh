#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=12:11:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/4/156.sh : -np 1 bash ./inp.5/0/1/2/3/15/17/18/151.sh : -np 1 bash ./inp.5/0/1/2/3/15/17/18/145.sh : -np 1 bash ./inp.5/0/1/2/3/15/17/140.script : -np 1 bash ./inp.5/0/1/2/3/15/136.sh : -np 1 bash ./inp.5/0/1/2/3/15/129.script : -np 1 bash ./inp.5/0/1/2/3/10/14/124.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/13/20/114.sh 
