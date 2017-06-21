#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=6:36:40
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/7/12/14/27/155.sh : -np 1 bash ./inp.3/0/1/7/12/14/21/151.sh : -np 1 bash ./inp.3/0/1/7/12/14/135.script : -np 1 bash ./inp.3/0/1/7/12/128.sh : -np 1 bash ./inp.3/0/1/5/10/98.sh : -np 1 bash ./inp.3/0/1/5/10/19/23/114.sh : -np 1 bash ./inp.3/0/1/5/10/19/104.sh : -np 1 bash ./inp.3/0/1/2/4/119.sh 
