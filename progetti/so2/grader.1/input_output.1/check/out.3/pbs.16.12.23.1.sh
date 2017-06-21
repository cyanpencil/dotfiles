#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=16:12:23
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/6/9/48.sh : -np 1 bash ./inp.3/0/6/8/57.script : -np 1 bash ./inp.3/0/6/8/13/15/72.sh : -np 1 bash ./inp.3/0/6/20/44.sh : -np 1 bash ./inp.3/0/1/7/12/24/164.script : -np 1 bash ./inp.3/0/1/7/12/24/159.sh : -np 1 bash ./inp.3/0/1/7/12/14/25/144.sh : -np 1 bash ./inp.3/0/1/7/12/14/139.script 
