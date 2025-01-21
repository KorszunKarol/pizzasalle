#!/bin/bash

rm -rf target/

mkdir -p target

echo "Compiling Java files..."
if ! javac -d target $(find src/main/java -name "*.java"); then
    echo "Compilation failed!"
    exit 1
fi

clear

echo "Starting PizziSalle application..."
echo "--------------------------------"
java -cp target com.pizzisalle.Main

echo "--------------------------------"
echo "Application terminated."