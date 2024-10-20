#!/bin/sh

#mvn clean package

java -jar grpcServer/target/grpcServer.jar &

sleep 5

java -jar grpcClient/target/grpcClient.jar