#!/usr/bin/env bash
./gradlew clean build -x test
docker build -t manager:1.0 --build-arg JAR_FILE=./build/libs/manager* .
docker run -p 8080:8080 -it manager:1.0