#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=0:45:13
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/7/12/24/161.sh : -np 1 bash ./inp.3/0/1/7/12/24/157.sh : -np 1 bash ./inp.3/0/1/7/12/14/28/152.script : -np 1 bash ./inp.3/0/1/7/12/14/21/148.script : -np 1 bash ./inp.3/0/1/7/12/14/136.sh : -np 1 bash ./inp.3/0/1/7/12/129.script : -np 1 bash ./inp.3/0/1/5/88.sh : -np 1 bash ./inp.3/0/1/5/10/19/23/110.sh 
