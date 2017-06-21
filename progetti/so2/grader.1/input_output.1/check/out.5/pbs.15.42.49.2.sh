#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=5:mpiruns=5
#PBS -A unutente
#PBS -l walltime=15:42:49
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/0/1/2/3/15/16/154.script : -np 1 bash ./inp.5/0/1/2/3/10/91.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/99.sh : -np 1 bash ./inp.5/0/1/2/3/10/11/13/20/111.script : -np 1 bash ./inp.5/0/1/2/3/10/11/13/104.sh 
