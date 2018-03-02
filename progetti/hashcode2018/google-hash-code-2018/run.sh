#!/bin/bash
inputf="input"
outputf="output"
sourcef="src"
binaryf="bin"

source="solution.cpp"
binary="solution"

# Check if the file name is passed.
if [ -z "$1" ]; then
  echo "No file name supplied"
  exit 0
fi

# Compile only if the source code is newer than the compiled binary.
if [ "./$binaryf/$binary" -ot "./$sourcef/$source" ]; then
  mkdir -p $binaryf
  echo -n "Compiling '$source'"
  g++ -std=c++14 ./$sourcef/$source -o ./$binaryf/$binary
  echo " -> done"
fi

# Run the code.
echo -n "Running '$1'"
./$binaryf/$binary < ./$inputf/$1 > ./$outputf/$1
echo " -> done"
