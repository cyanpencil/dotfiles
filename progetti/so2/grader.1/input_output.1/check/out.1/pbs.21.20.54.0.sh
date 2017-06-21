#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=21:20:54
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/5.sh : -np 1 bash ./inp.1/0/9.script : -np 1 bash ./inp.1/0/7/10/15/25/34.script : -np 1 bash ./inp.1/0/7/10/15/18/45.script : -np 1 bash ./inp.1/0/7/10/15/18/38.sh : -np 1 bash ./inp.1/0/17.script : -np 1 bash ./inp.1/0/1/2/73.script : -np 1 bash ./inp.1/0/1/2/5/6/9/17/27/88.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/98.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/114.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/108.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/102.script 
