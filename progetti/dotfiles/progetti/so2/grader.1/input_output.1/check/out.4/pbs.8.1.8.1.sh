#PBS -L select=2:ncpus=4:mpiprocs=4+1:ncpus=1:mpiruns=1
#PBS -A unutente
#PBS -l walltime=8:1:8
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/1/2/6/128.sh : -np 1 bash ./inp.4/0/1/2/123.sh : -np 1 bash ./inp.4/0/1/2/119.sh : -np 1 bash ./inp.4/0/11/23.sh : -np 1 bash ./inp.4/0/11/17.sh : -np 1 bash ./inp.4/0/1/4/7/147.script : -np 1 bash ./inp.4/0/1/4/7/13/159.script : -np 1 bash ./inp.4/0/1/4/141.sh : -np 1 bash ./inp.4/0/1/28/163.script 
