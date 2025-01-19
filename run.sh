#!/bin/bash
# Clean any stray .class files in the project directory
find . -name "*.class" -type f -delete
rm -rf target
mkdir -p target
find src/main/java -name "*.java" > sources.txt
javac -d target @sources.txt
java -cp target com.pizzisalle.Main