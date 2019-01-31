#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=3:21:1
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/7/25/63.sh : -np 1 bash ./inp.2/0/7/21/75.sh : -np 1 bash ./inp.2/0/7/21/71.sh : -np 1 bash ./inp.2/0/7/21/67.sh : -np 1 bash ./inp.2/0/1/8/122.script : -np 1 bash ./inp.2/0/1/8/12/22/132.script : -np 1 bash ./inp.2/0/1/8/12/128.sh : -np 1 bash ./inp.2/0/1/8/118.script 
