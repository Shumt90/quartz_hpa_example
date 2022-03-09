#!/bin/sh

exec java $JAVA_OPTS -jar -Dspring.profiles.active=cloud /usr/app/app.jar
