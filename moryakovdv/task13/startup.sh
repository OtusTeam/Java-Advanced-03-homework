#!/bin/sh
mvn clean install
cd api13

#build app for docker
docker build -t task13docker .

#no need to run it now
###docker run -p 80:8082 task13docker

#set tag to application
docker tag task13docker moryakovdv/task13docker
#push it to docker hub
docker push moryakovdv/task13docker

cd ..
# create helm chart
helm create otus-task13

#create templates and lint
helm template otus-task13 --values api13/values.yaml --debug
helm lint otus-task13 --values api13/values.yaml --debug


###Deploy
helm upgrade --install otus-task13-kubernetes --values api13/values.yaml helm
