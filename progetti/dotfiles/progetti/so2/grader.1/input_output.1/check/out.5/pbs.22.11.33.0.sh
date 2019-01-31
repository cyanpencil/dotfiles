#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=22:11:33
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/5.sh : -np 1 bash ./inp.5/1.sh : -np 1 bash ./inp.5/0/16.script : -np 1 bash ./inp.5/0/1/5/7/54.script : -np 1 bash ./inp.5/0/1/5/44.sh : -np 1 bash ./inp.5/0/1/5/28/64.script : -np 1 bash ./inp.5/0/1/2/3/79.script : -np 1 bash ./inp.5/0/1/23/24/73.script 
