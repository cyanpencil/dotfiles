#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=9:51:34
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/115.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/104.script : -np 1 bash ./inp.1/0/1/11/56.script : -np 1 bash ./inp.1/0/1/11/24/61.script : -np 1 bash ./inp.1/0/10.sh : -np 1 bash ./inp.1/0/1/3/170.sh : -np 1 bash ./inp.1/0/1/2/8/26/160.script : -np 1 bash ./inp.1/0/1/2/8/26/155.sh : -np 1 bash ./inp.1/0/1/2/8/151.script : -np 1 bash ./inp.1/0/1/2/8/147.script : -np 1 bash ./inp.1/0/1/2/4/16/129.script : -np 1 bash ./inp.1/0/1/2/4/124.script 
