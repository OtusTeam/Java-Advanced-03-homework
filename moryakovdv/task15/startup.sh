#!/bin/sh
mvn clean install
cd api15

#build app for docker
docker build -t task15docker .

#no need to run it now
###docker run -p 80:8082 task15docker

#set tag to application
docker tag task15docker moryakovdv/task15docker
#push it to docker hub
docker push moryakovdv/task15docker

cd ..
# create helm chart
helm create otus-task15

#create templates and lint
helm template otus-task15 --values api15/values.yaml --debug
helm lint otus-task15 --values api15/values.yaml --debug


###Deploy
helm upgrade --install otus-task15-kubernetes --values api15/values.yaml helm
