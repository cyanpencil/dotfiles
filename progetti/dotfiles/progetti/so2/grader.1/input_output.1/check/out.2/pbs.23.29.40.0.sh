#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=23:29:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/2/26/35.sh : -np 1 bash ./inp.2/0/2/23/30.sh : -np 1 bash ./inp.2/0/2/20/21.script : -np 1 bash ./inp.2/0/2/20/17.script : -np 1 bash ./inp.2/0/2/19/25.script : -np 1 bash ./inp.2/0/2/13/41.script : -np 1 bash ./inp.2/0/2/13/18/53.script : -np 1 bash ./inp.2/0/2/10.sh 
