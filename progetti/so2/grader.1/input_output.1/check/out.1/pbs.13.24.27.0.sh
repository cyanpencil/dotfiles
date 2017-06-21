#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=13:24:27
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/7.sh : -np 1 bash ./inp.1/0/7/10/21.sh : -np 1 bash ./inp.1/0/7/10/15/25/36.script : -np 1 bash ./inp.1/0/7/10/15/25/32.script : -np 1 bash ./inp.1/0/15.script : -np 1 bash ./inp.1/0/1/47.script : -np 1 bash ./inp.1/0/1/2/5/75.script : -np 1 bash ./inp.1/0/1/2/5/6/9/86.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/80.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/111.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/106.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/100.script 
