#!/bin/bash

rsync -ra ~/asl-fall18-project www.cyanpencil.xyz:~/ --info=progress2 --filter=':- .gitignore'
