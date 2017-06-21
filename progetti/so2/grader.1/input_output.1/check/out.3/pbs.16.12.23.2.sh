#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=6:mpiruns=6
#PBS -A unutente
#PBS -l walltime=16:12:23
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.3/0/1/7/12/127.script : -np 1 bash ./inp.3/0/1/5/91.script : -np 1 bash ./inp.3/0/1/5/10/97.script : -np 1 bash ./inp.3/0/1/5/10/19/103.script : -np 1 bash ./inp.3/0/1/2/4/118.script : -np 1 bash ./inp.3/0/1/22/77.script 
