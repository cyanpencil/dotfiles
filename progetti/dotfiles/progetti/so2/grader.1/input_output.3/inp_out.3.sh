test -f 3.awk || { echo "Manca il file 3.awk" > out.3/res.err.txt; exit; }
gawk -v filename=out.3/wrong.3.csv -f 3.awk inp.3/demand.csv inp.3/costs.csv > out.3/res.out.txt 2> out.3/res.err.txt
