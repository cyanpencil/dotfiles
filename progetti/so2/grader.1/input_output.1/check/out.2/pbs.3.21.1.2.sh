#PBS -L select=0:ncpus=8:mpiprocs=8+1:ncpus=5:mpiruns=5
#PBS -A unutente
#PBS -l walltime=3:21:1
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.2/0/1/3/84.script : -np 1 bash ./inp.2/0/1/3/80.script : -np 1 bash ./inp.2/0/1/3/5/6/10/17/28/104.script : -np 1 bash ./inp.2/0/1/3/4/9/113.sh : -np 1 bash ./inp.2/0/1/24/139.sh 
