#PBS -L select=2:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=21:38:45
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/1/5/8/14/22/109.script : -np 1 bash ./inp.4/0/1/5/8/14/17/104.script : -np 1 bash ./inp.4/0/1/2/6/18/133.script : -np 1 bash ./inp.4/0/1/2/6/129.script : -np 1 bash ./inp.4/0/11/24.script : -np 1 bash ./inp.4/0/11/18.script : -np 1 bash ./inp.4/0/1/4/7/15/152.script : -np 1 bash ./inp.4/0/1/4/7/148.sh 
