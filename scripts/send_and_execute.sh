#!/bin/bash
SERVER="www.cyanpencil.xyz"

if [[ $# -ge 1 ]]; then
    SERVER=$1
fi

cd ~/progetti/eccellenza/sentimentanalysis
rsync -rav --exclude='.git' --filter=':- .gitignore' . $SERVER:/home/luca/progetti/eccellenza/sentimentanalysis/
ssh -t -CY -c blowfish-cbc,arcfour $SERVER "bash -ic 'cd progetti/eccellenza/sentimentanalysis; export PYTHONPATH=/home/luca/progetti/eccellenza/sentimentanalysis; pyenv local st-3.5.1; python3 spiketrap/lib/classifier_example.py'"
