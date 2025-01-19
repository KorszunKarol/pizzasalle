#!/bin/bash
rm -rf target
mkdir -p target
find src/main/java -name "*.java" > sources.txt
javac -d target @sources.txt
java -cp target com.pizzisalle.Main