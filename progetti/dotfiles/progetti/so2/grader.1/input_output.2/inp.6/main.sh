#!/bin/bash
bash 2.sh 0 0 0 > ../out.6/res.out.txt 2> ../out.6/res.err.txt &
pid_main=$!
if [ $(ps -p $pid_main | wc -l | awk '{print $1}') -ne 1 ]; then echo Error: 2.sh still executing but it was wrongly launched > ../out.6/res.main.txt; fi
