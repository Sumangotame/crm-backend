# Use a small JDK image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy built jar
COPY target/crm-0.0.1-SNAPSHOT.jar app.jar

# Expose the port from environment variable
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
