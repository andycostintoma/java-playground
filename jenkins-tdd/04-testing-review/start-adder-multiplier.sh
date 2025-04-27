#!/bin/sh

echo "Starting the 'adder' project on debug port 5005"
cd adder
./mvnw quarkus:dev -Ddebug=5005 &
ADDER_PID=$!
sleep 5
cd ..

echo "Starting the 'multiplier' project on debug port 5006"
cd multiplier
./mvnw quarkus:dev -Ddebug=5006 &
MULTIPLIER_PID=$!
sleep 5
cd ..

