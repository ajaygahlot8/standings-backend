FROM openjdk:11.0.4-jdk-slim

ENV ENV_PORT=8080

COPY build/libs/manager.jar .

CMD java \
  ${JAVA_OPTS} \
  -jar manager.jar

EXPOSE ${ENV_PORT}