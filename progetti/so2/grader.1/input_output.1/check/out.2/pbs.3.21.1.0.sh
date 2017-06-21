#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=3:21:1
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0.script : -np 1 bash ./inp.2/0/7/55.script : -np 1 bash ./inp.2/0/2/7.script : -np 1 bash ./inp.2/0/2/26/37.sh : -np 1 bash ./inp.2/0/2/20/22.sh : -np 1 bash ./inp.2/0/2/20/18.sh : -np 1 bash ./inp.2/0/2/19/27.script : -np 1 bash ./inp.2/0/2/13/44.sh 
