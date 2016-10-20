#!/bin/bash
#scriptino per compilare e eseguire l'homework di java :)

cd ~

javac -d comp $(find gapp -name "*.java")
java -cp comp gapp.ulg.test.PartialGrade
