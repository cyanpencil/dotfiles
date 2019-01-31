test -f 3.awk || { echo "Manca il file 3.awk" > out.2/res.err.txt; exit; }
gawk -v filename=out.2/wrong.2.csv -f 3.awk inp.2/demand.csv inp.2/costs.csv > out.2/res.out.txt 2> out.2/res.err.txt
