#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=8:1:8
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/7.sh : -np 1 bash ./inp.4/0/3/27.script : -np 1 bash ./inp.4/0/3/16/26/71.sh : -np 1 bash ./inp.4/0/3/16/19/21/27/66.sh : -np 1 bash ./inp.4/0/3/12/36.sh : -np 1 bash ./inp.4/0/3/12/32.sh : -np 1 bash ./inp.4/0/1/83.script : -np 1 bash ./inp.4/0/1/78.sh : -np 1 bash ./inp.4/0/1/5/8/14/22/108.sh : -np 1 bash ./inp.4/0/1/5/8/14/17/99.sh : -np 1 bash ./inp.4/0/1/5/8/14/17/103.sh : -np 1 bash ./inp.4/0/1/2/6/18/137.script 
