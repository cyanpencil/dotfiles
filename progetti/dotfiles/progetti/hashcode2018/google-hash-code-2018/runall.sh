#!/bin/bash
inputf="input"

# Loop on all files
for file_path in ./$inputf/*; do
  [ -f "$file_path" ] || continue
  [ -s "$file_path" ] || continue
  file_name=$(basename $file_path)
  bash ./run.sh $file_name &
done
