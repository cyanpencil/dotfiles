#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=12:11:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/4.script : -np 1 bash ./inp.5/0/15.sh : -np 1 bash ./inp.5/0/1/5/43.script : -np 1 bash ./inp.5/0/1/5/28/63.sh : -np 1 bash ./inp.5/0/1/24.script : -np 1 bash ./inp.5/0/1/2/3/77.script : -np 1 bash ./inp.5/0/1/23/72.sh : -np 1 bash ./inp.5/0/1/23/67.script 
