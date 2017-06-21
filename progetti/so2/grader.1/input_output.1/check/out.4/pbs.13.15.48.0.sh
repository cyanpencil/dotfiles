#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=13:15:48
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/9/15.script : -np 1 bash ./inp.4/0/5.sh : -np 1 bash ./inp.4/0/3/16/49.sh : -np 1 bash ./inp.4/0/3/16/19/21/60.script : -np 1 bash ./inp.4/0/3/12/30.sh : -np 1 bash ./inp.4/0/3/10/41.script : -np 1 bash ./inp.4/0/1/80.sh : -np 1 bash ./inp.4/0/1/5/8/93.script : -np 1 bash ./inp.4/0/1/5/85.sh : -np 1 bash ./inp.4/0/1/5/8/14/97.script : -np 1 bash ./inp.4/0/1/5/8/14/22/112.sh : -np 1 bash ./inp.4/0/1/5/8/14/17/106.script 
