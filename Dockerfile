# Use Java 17 and Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build JAR
RUN mvn clean package -DskipTests

# Use slim Java image to run
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/property-rental-api.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
