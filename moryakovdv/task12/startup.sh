#!/bin/sh
mvn clean install
cd api12
docker build -t task12docker .
docker run -p 80:8082 task12docker