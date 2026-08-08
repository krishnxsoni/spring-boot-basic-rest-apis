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

- Create student (example for this repo)

  The application in this repository exposes a students endpoint on port 8010 in some sample setups. Below is a sample Postman request and an equivalent cURL command to create a student with required headers (auth-token and x-api-key).

  Postman request:

  ```text
  POST 'http://localhost:8010/api/students/' \
    --header 'auth-token: 12345' \
    --header 'x-api-key: secret123' \
    --header 'Content-Type: application/json' \
    --body '{
      "id" : 1,
      "name" : "Rahul",
      "email" : "rahul@gmail.com"
  }'
  ```

  Equivalent cURL:

  ```bash
  curl -X POST 'http://localhost:8010/api/students/' \
    -H 'auth-token: 12345' \
    -H 'x-api-key: secret123' \
    -H 'Content-Type: application/json' \
    -d '{"id":1,"name":"Rahul","email":"rahul@gmail.com"}'
  ```

Adjust endpoints and payloads to reflect the actual controllers in the repository.


## Contributing

Contributions, issues, and feature requests are welcome. Please open an issue first for major changes and follow the repository's contribution guidelines if present.
