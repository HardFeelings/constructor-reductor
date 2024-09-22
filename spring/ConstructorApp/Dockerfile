FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
WORKDIR /opt/app
COPY ./app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]