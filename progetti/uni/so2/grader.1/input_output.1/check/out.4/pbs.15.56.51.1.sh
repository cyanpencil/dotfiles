#PBS -L select=2:ncpus=4:mpiprocs=4+1:ncpus=1:mpiruns=1
#PBS -A unutente
#PBS -l walltime=15:56:51
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.4/0/1/5/8/14/22/107.script : -np 1 bash ./inp.4/0/1/5/8/14/17/98.script : -np 1 bash ./inp.4/0/1/5/8/14/17/102.script : -np 1 bash ./inp.4/0/1/2/6/131.script : -np 1 bash ./inp.4/0/1/2/122.script : -np 1 bash ./inp.4/0/1/2/118.script : -np 1 bash ./inp.4/0/1/4/7/150.sh : -np 1 bash ./inp.4/0/1/4/7/13/162.sh : -np 1 bash ./inp.4/0/1/4/23/145.script 
