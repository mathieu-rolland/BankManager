FROM openjdk:8-jdk-alpine
VOLUME /tmp

RUN apk update && apk add bash

ARG JAR_FILE
ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY target/${JAR_FILE} /app/bank-manager.jar

EXPOSE 8080

ENTRYPOINT [ "java" , "-Dspring.profiles.active=devlocal" , "-jar" , "/app/bank-manager.jar" ]
