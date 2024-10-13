FROM alpine:latest

RUN apk add --no-cache freetype-dev

FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]