# 1. Update this to Java 21 so Maven can compile for Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build JAR
RUN mvn clean package -DskipTests

# 2. Use the JRE (Runtime) for the final image to keep it small
# Note: Changed from jdk-alpine to jre for a smaller, more secure image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/property-rental-api.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]