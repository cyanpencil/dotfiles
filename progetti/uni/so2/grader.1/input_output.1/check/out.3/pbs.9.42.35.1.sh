#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=9:42:35
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/6/9/47.script : -np 1 bash ./inp.3/0/6/9/11/55.sh : -np 1 bash ./inp.3/0/6/8/66.sh : -np 1 bash ./inp.3/0/6/8/61.script : -np 1 bash ./inp.3/0/6/8/13/15/71.script : -np 1 bash ./inp.3/0/1/7/12/24/158.script : -np 1 bash ./inp.3/0/1/7/12/14/28/153.sh : -np 1 bash ./inp.3/0/1/7/12/14/138.sh 
