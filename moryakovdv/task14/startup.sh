#!/bin/sh

##mvn clean install

cd api14/infra

#build multiply containers with docker-compose
#daemon mode not used to display console output
docker-compose -p otus up
