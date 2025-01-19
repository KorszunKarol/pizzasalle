#!/bin/bash

# Clear previous build
rm -rf target/

# Create target directory
mkdir -p target

# Compile all Java files
echo "Compiling Java files..."
if ! javac -d target $(find src/main/java -name "*.java"); then
    echo "Compilation failed!"
    exit 1
fi

# Clear screen before running
clear

# Run the application
echo "Starting PizziSalle application..."
echo "--------------------------------"
java -cp target com.pizzisalle.Main

# Add exit message
echo "--------------------------------"
echo "Application terminated."