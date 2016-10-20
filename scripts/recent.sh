#!/bin/bash

sudo find ~ -maxdepth 4 -type f -mtime -1 -printf "%T@-%Tk:%TM - %f\n" | sort -nr | cut -d- -f2-
