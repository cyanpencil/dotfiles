#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=21:38:45
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/9/14.sh : -np 1 bash ./inp.4/0/4.script : -np 1 bash ./inp.4/0/3/16/48.script : -np 1 bash ./inp.4/0/3/16/19/59.sh : -np 1 bash ./inp.4/0/3/16/19/54.script : -np 1 bash ./inp.4/0/3/16/19/21/27/67.script : -np 1 bash ./inp.4/0/3/12/38.sh : -np 1 bash ./inp.4/0/3/12/29.script : -np 1 bash ./inp.4/0/3/10/20/44.sh : -np 1 bash ./inp.4/0/1/79.script : -np 1 bash ./inp.4/0/1/5/92.script : -np 1 bash ./inp.4/0/1/5/8/14/22/25/116.sh 
