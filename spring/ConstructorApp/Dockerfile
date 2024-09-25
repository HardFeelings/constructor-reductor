
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package


FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/ConstructorApp-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "ConstructorApp-0.0.1-SNAPSHOT.jar"]