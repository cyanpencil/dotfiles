#!/bin/bash

{ a=$(w3m -cols 999 -dump http://en.wikipedia.org/wiki/`date +%B_%d` | sed -n '/Events\[/,/Births\[/ p' | sed -n 's/^.*• //p' | shuf -n 1); printf "\e[s;\e[1;1H\e[35mHappened on $(date +'%B %d'): $a\e[0K\e[u" 2>/dev/null ;} &
