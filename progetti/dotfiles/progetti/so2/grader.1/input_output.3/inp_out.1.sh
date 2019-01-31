test -f 3.awk || { echo "Manca il file 3.awk" > out.1/res.err.txt; exit; }
gawk -v filename=out.1/wrong.1.csv -f 3.awk inp.1/demand.csv inp.1/costs.csv > out.1/res.out.txt 2> out.1/res.err.txt
