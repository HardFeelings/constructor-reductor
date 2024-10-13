FROM alpine:3.20
RUN apk add --no-cache fontconfig
COPY ./fonts /usr/share/fonts/truetype/
#RUN fc-cache -f -v

FROM openjdk:17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
