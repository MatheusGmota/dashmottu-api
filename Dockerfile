# Fase de construção
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Fase de execução
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
RUN adduser --system --group appuser
USER appuser

ARG JAR_FILE=target/dashmottu-*.jar
COPY --from=build /app/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]