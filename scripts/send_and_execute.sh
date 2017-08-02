#!/bin/bash


cd ~/progetti/eccellenza/sentimentanalysis
rsync -rav --exclude='.git' --filter=':- .gitignore' . www.cyanpencil.xyz:/home/luca/progetti/eccellenza/sentimentanalysis/
ssh -CY -c blowfish-cbc,arcfour www.cyanpencil.xyz "bash -ic 'cd progetti/eccellenza/sentimentanalysis; export PYTHONPATH=/home/luca/progetti/eccellenza/sentimentanalysis; pyenv local st-3.5.1; python3 spiketrap/lib/classifier_example.py'"
