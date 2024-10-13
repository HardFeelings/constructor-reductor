# Базовый образ с Ubuntu
FROM alpine
RUN apk add --no-cache freetype freetype-dev
RUN apk add --no-cache fontconfig


# Копируем шрифты
COPY ./fonts /usr/share/fonts/truetype/
# Предполагается что шрифты находятся в папке fonts рядом с Dockerfile

# Обновляем кеш шрифтов
RUN fc-cache -f -v

# Базовый образ с OpenJDK
FROM openjdk:17-jdk

# Копируем jar-файл
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Определяем точку входа
ENTRYPOINT ["java", "-jar", "/app.jar"]
