#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=12:57:20
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/7/57.script : -np 1 bash ./inp.2/0/2/8.sh : -np 1 bash ./inp.2/0/2/20/19.script : -np 1 bash ./inp.2/0/2/19/23.script : -np 1 bash ./inp.2/0/2/13/45.script : -np 1 bash ./inp.2/0/7/29/77.sh : -np 1 bash ./inp.2/0/7/25/64.script : -np 1 bash ./inp.2/0/7/21/72.script 
