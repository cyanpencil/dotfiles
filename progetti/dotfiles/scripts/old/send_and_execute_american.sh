#!/bin/bash
SERVER="12.31.244.54"

if [[ $# -ge 1 ]]; then
    SERVER=$1
fi

cd ~/progetti/eccellenza/sentimentanalysis
rsync -rav --exclude='.git' --filter=':- .gitignore' -e "ssh -p2422" . $SERVER:/home/luca/dev
ssh -p2422 -t -CY $SERVER "bash -ic 'cd dev; export PYTHONPATH=/home/luca/dev; pyenv local 3.6.2; python spiketrap/lib/classifier_example.py'"
