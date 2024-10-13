FROM ubuntu:20.04

RUN apt-get update && apt-get install -y \
       libfreetype6 \
       && apt-get clean \
       && rm -rf /var/lib/apt/lists/*

FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]