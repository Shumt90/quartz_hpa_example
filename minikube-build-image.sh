#!/bin/bash

#prepare and build jar
export JAVA_HOME=~/app/openjdk-17.0.2_linux-x64_bin/jdk-17.0.2
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
mvn -version
mvn clean install -DskipTests
cp target/*.jar

#prepare and build docker image

IMAGE_NAME=autoscale-worker
cp Dockerfile Dockerfile
cp entrypoint.sh entrypoint.sh
eval $(minikube docker-env)
docker build . -t "$IMAGE_NAME"