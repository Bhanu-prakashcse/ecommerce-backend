# --- Stage 1: Build the Spring Boot app ---
FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# --- Stage 2: Run the JAR file ---
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
