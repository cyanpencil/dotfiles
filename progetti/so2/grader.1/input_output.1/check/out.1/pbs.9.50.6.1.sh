#PBS -L select=3:ncpus=4:mpiprocs=4
#PBS -A unutente
#PBS -l walltime=9:50:6
mpirun --hostfile $PBS_NODEFILE -np 1 bash ./inp.1/0/1/2/5/6/9/17/87.script : -np 1 bash ./inp.1/0/1/2/5/6/9/17/27/97.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/28/112.script : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/107.sh : -np 1 bash ./inp.1/0/1/2/5/6/9/12/22/101.sh : -np 1 bash ./inp.1/0/1/11/59.sh : -np 1 bash ./inp.1/0/1/11/54.script : -np 1 bash ./inp.1/0/1/11/24/64.sh : -np 1 bash ./inp.1/0/1/3/172.sh : -np 1 bash ./inp.1/0/1/2/8/153.script : -np 1 bash ./inp.1/0/1/2/8/149.script : -np 1 bash ./inp.1/0/1/2/8/14/23/168.script 
