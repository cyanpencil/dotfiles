#PBS -L select=1:ncpus=8:mpiprocs=8
#PBS -A unutente
#PBS -l walltime=23:24:12
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.5/3.sh : -np 1 bash ./inp.5/0/1/5/48.sh : -np 1 bash ./inp.5/0/1/5/28/62.script : -np 1 bash ./inp.5/0/14.script : -np 1 bash ./inp.5/0/1/2/76.script : -np 1 bash ./inp.5/0/1/23/66.sh : -np 1 bash ./inp.5/0/1/2/3/10/88.script : -np 1 bash ./inp.5/0/1/2/3/10/83.sh 
