FROM alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirror.yandex.ru/g' /etc/apk/repositories
RUN apk update && apk add --no-cache freetype freetype-dev fontconfig
RUN apk add --no-cache fontconfig

COPY ./fonts /usr/share/fonts/truetype/
RUN fc-cache -f -v

FROM openjdk:17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
