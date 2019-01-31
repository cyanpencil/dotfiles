#PBS -L select=2:ncpus=4:mpiprocs=4+1:ncpus=3:mpiruns=3
#PBS -A unutente
#PBS -l walltime=13:24:27
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/11.script : -np 1 bash ./inp.1/0/1/11/24/63.script : -np 1 bash ./inp.1/0/1/3/171.script : -np 1 bash ./inp.1/0/1/2/8/152.sh : -np 1 bash ./inp.1/0/1/2/8/14/21/167.sh : -np 1 bash ./inp.1/0/1/2/8/14/163.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/116.script : -np 1 bash ./inp.1/0/1/2/4/20/125.script : -np 1 bash ./inp.1/0/1/2/4/16/130.sh : -np 1 bash ./inp.1/0/1/2/13/19/144.sh : -np 1 bash ./inp.1/0/1/2/13/135.script 
