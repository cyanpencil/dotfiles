#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=9:50:6
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/8.script : -np 1 bash ./inp.1/4.script : -np 1 bash ./inp.1/0/7/10/15/28.sh : -np 1 bash ./inp.1/0/7/10/15/25/33.sh : -np 1 bash ./inp.1/0/7/10/15/18/44.sh : -np 1 bash ./inp.1/0/7/10/15/18/37.script : -np 1 bash ./inp.1/0/16.sh : -np 1 bash ./inp.1/0/1/48.sh : -np 1 bash ./inp.1/0/12.sh : -np 1 bash ./inp.1/0/1/2/72.sh : -np 1 bash ./inp.1/0/1/2/5/76.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/81.script 
