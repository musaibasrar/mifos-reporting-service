# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app

# Copy pom and src files
COPY pom.xml .
COPY src ./src

# Build the application with tests skipped
RUN mvn clean install -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the internal port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
