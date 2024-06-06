#!/bin/sh
##start benchmarking with 1 warmup, 1 forks, 5 iterations
java -jar target/jmh.jar 1 1 5 > benchmark.log
№java -jar target/jmh.jar 1 1 5