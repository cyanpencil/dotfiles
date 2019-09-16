#!/bin/bash

[[ $# -eq 0 ]] && echo "Usage $0 <file>" && exit 1
#[[ `which bellazio ; echo $?` -ne 0 ]] && exit 1

#stolen from some public github repo
APIKEY="45573aae941396e0e4f385256dfdf3b9fe314a321ffdf8f0d9ab4c117a2ac2f2"
APIKEY="3c052e9a7339f3a73f00bd67baea747e47f59ee6c1596e59590fd953d00ce519"

echo uploading...
result=$(vt scan file "$1" --apikey "$APIKEY" | grep -o "[a-Z0-9=]\{20,\}" | tr '\n' ' ')
[[ ${#result} -eq 0 ]] && echo "Error uploading file" && exit 1
echo done!
echo "$result"

cmd="vt analysis ${result% } --apikey $APIKEY"

while [[ $($cmd | grep -c "queued") -ge 1 ]]; do 
	echo "analyzing..."
	sleep 5
done
echo $cmd
eval "$cmd"
