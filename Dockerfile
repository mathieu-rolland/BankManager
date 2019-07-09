FROM openjdk:8-jdk-alpine
VOLUME /data

RUN apk update && apk add bash

ARG JAR_FILE
ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY target/${JAR_FILE} /app/bank-manager.jar

ENV DATABASE_PORT=""
ENV DATABASE_PORT=""
ENV DATABASE_NAME=""
ENV DATABASE_PASSWD=""

EXPOSE 8080

ENTRYPOINT [ "java" , "-Dspring.profiles.active=prod" , "-jar" , "/app/bank-manager.jar" ]
