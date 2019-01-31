#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=21:20:54
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/5/6/78.sh : -np 1 bash ./inp.1/0/1/11/60.script : -np 1 bash ./inp.1/0/1/11/55.sh : -np 1 bash ./inp.1/0/1/11/24/65.script : -np 1 bash ./inp.1/0/1/3/174.sh : -np 1 bash ./inp.1/0/1/3/169.script : -np 1 bash ./inp.1/0/1/2/8/26/158.script : -np 1 bash ./inp.1/0/1/2/8/26/154.script : -np 1 bash ./inp.1/0/1/2/8/150.sh : -np 1 bash ./inp.1/0/1/2/8/14/165.script : -np 1 bash ./inp.1/0/1/2/4/20/128.sh : -np 1 bash ./inp.1/0/1/2/4/118.script 
