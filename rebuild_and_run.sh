#!/usr/bin/env bash

IMAGE_NAME=$1

mvn clean install -DskipTests
(cd target && sh ../build-image.sh "$IMAGE_NAME")

docker stop autoscale_test

docker run \
-e DB_SCHEMA=autoscale_test \
-e DB_POSTGRES_HOST=host.docker.internal \
-e DB_POSTGRES_PORT=5432 \
-e DB_USER=postgres \
-e DB_PASS=postgres \
--rm \
-p 8080:8080 \
--name autoscale_test \
 "$IMAGE_NAME"
