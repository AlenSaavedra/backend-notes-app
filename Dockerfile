# Use a base image of OpenJDK
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project files into the Docker image
COPY . /app

# Install Maven (if necessary)
RUN apt-get update && apt-get install -y maven

# Give permissions to mvnw (if necessary)
RUN chmod +x ./mvnw

# Run the command to build the project
RUN ./mvnw clean install

# Expose the port for the Spring Boot application
EXPOSE 8080

# Command to start the application
CMD ["./mvnw", "spring-boot:run"]
