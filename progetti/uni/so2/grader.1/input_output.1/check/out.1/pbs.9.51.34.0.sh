#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=9:51:34
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/1.sh : -np 1 bash ./inp.1/0/7/10/25.sh : -np 1 bash ./inp.1/0/7/10/15/31.script : -np 1 bash ./inp.1/0/7/10/15/25/35.sh : -np 1 bash ./inp.1/0/7/10/15/18/46.sh : -np 1 bash ./inp.1/0/7/10/15/18/40.sh : -np 1 bash ./inp.1/0/14.sh : -np 1 bash ./inp.1/0/1/2/74.sh : -np 1 bash ./inp.1/0/1/2/69.script : -np 1 bash ./inp.1/0/1/2/5/6/9/79.script : -np 1 bash ./inp.1/0/1/2/5/6/9/17/27/95.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/99.sh 
