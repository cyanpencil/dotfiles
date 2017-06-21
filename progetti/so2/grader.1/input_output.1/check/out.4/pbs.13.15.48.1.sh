#PBS -L select=1:ncpus=4:mpiprocs=4+1:ncpus=2:mpiruns=2
#PBS -A unutente
#PBS -l walltime=13:15:48
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/1/2/6/18/135.script : -np 1 bash ./inp.4/0/1/2/125.sh : -np 1 bash ./inp.4/0/11/20.script : -np 1 bash ./inp.4/0/10.script : -np 1 bash ./inp.4/0/1/4/7/149.script : -np 1 bash ./inp.4/0/1/4/143.sh 
