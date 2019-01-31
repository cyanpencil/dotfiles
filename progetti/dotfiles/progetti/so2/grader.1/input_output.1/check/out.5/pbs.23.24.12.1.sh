#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=23:24:12
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/19.sh : -np 1 bash ./inp.5/0/1/12/32.sh : -np 1 bash ./inp.5/0/1/2/4/6/8/9/175.script : -np 1 bash ./inp.5/0/1/2/4/6/25/168.sh : -np 1 bash ./inp.5/0/1/2/4/6/163.sh : -np 1 bash ./inp.5/0/1/2/4/6/159.sh : -np 1 bash ./inp.5/0/1/2/4/155.script : -np 1 bash ./inp.5/0/1/2/3/15/17/22/143.script 
