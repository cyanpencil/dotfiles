#!/bin/bash
playerctl metadata | sed -r "s/.*artist[^\[]*\[[\'\"]([^\'\"]*).*title[\'\"][^\'\"]*[\'\"]([^\'\"]*).*/\1 - \2\n/"
