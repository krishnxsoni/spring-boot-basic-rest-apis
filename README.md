# spring-boot-basic-rest-apis

A small Spring Boot example repository demonstrating the basics of building RESTful APIs using Spring Boot. This project is intended as a starter template for learning or bootstrapping simple REST services, including configuration, validation, error handling, and basic tests.

## Features

- Spring Boot (Starter web)
- Simple REST endpoints (GET, POST, PUT, DELETE)
- Request validation (javax.validation / jakarta.validation)
- Global exception handling
- Unit and integration test examples
- Maven build

> NOTE: Update this README to match the actual implemented endpoints and configuration in the repository.

## Prerequisites

- Java 11 or later (Java 17 recommended)
- Maven 3.6+
- Git

## Quick start

1. Clone the repo

   git clone https://github.com/krishnxsoni/spring-boot-basic-rest-apis.git
   cd spring-boot-basic-rest-apis

2. Build the project

   mvn clean package

3. Run the application

- Using Maven

  mvn spring-boot:run

- Using the packaged jar

  java -jar target/*.jar

The application will start on port 8080 by default. Change the port or other properties in `src/main/resources/application.properties` or via environment variables.

## Configuration

Common properties (application.properties / application.yml):

- server.port — application port
- spring.datasource.* — datasource configuration if using a DB

You can override properties when launching the app:

  mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090"

or

  java -jar target/app.jar --server.port=9090

## Example API endpoints

Below are example endpoints you can adapt to what this repository actually implements. Replace with real paths when updating this README.

- GET /api/health
  - Response: 200 OK
  - Body: { "status": "UP" }

- GET /api/items
  - Response: 200 OK
  - Body: [ { "id": 1, "name": "Item A" }, ... ]

- GET /api/items/{id}
  - Response: 200 OK or 404 Not Found

- POST /api/items
  - Request Body: { "name": "Item A" }
  - Response: 201 Created

cURL examples:

- Health check

  curl -i http://localhost:8080/api/health

- Create item

  curl -X POST http://localhost:8080/api/items \
    -H "Content-Type: application/json" \
    -d '{"name":"New Item"}'

Adjust endpoints and payloads to reflect the actual controllers in the repository.

## Tests

Run unit and integration tests with:

  mvn test

## Docker (optional)

You can add a Dockerfile to containerize the app. A simple example:

  FROM eclipse-temurin:17-jdk-jammy
  COPY target/*.jar app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]

Build and run:

  docker build -t spring-boot-basic-rest-apis .
  docker run -p 8080:8080 spring-boot-basic-rest-apis

## Contributing

Contributions, issues, and feature requests are welcome. Please open an issue first for major changes and follow the repository's contribution guidelines if present.

## License

Specify your license here (e.g., MIT, Apache-2.0). If unsure, add a LICENSE file to the repository and reference it here.

---

If you want, I can update the README to include the actual endpoints and examples by scanning the repository — shall I do that now?