# University System

A Spring Boot project demonstrating the use of Docker, Java, JUnit, RESTful APIs, Exception Handling, Object-Oriented Programming (OOP), Swagger, Integration Tests, Caffeine, Caching, and MySQL. This project does not include a front-end.

## Features

- RESTful APIs for university system operations
- Exception handling
- Object-Oriented Programming (OOP) principles
- Integration tests with JUnit
- API documentation with Swagger
- Caching with Caffeine
- Docker for containerization
- MySQL for the database

## Technologies Used

- Spring Boot
- Docker
- Java
- JUnit
- RESTful APIs
- Swagger
- Caffeine
- MySQL

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Docker
- MySQL

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/ashkanfrs/university.git
    ```
2. Navigate to the project directory:
    ```bash
    cd university
    ```
3. Set up the MySQL database:
    ```sql
    CREATE DATABASE university;
    ```
4. Update the `application.properties` file with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/university
    spring.datasource.username=your-username
    spring.datasource.password=your-password

    spring.jpa.hibernate.ddl-auto=update
    ```
5. Build the project:
    ```bash
    mvn clean install
    ```
6. Run the application using Docker:
    ```bash
    docker build -t university-system .
    docker run -p 8080:8080 university-system
    ```

### Usage

1. The back-end server will start on `http://localhost:8080`.
2. Use tools like Postman or curl to interact with the API endpoints.

### API Documentation

API documentation is available via Swagger. Once the application is running, navigate to `http://localhost:8080/swagger-ui.html` to view and test the available endpoints.

## Running Tests

To run the integration tests:
```bash
mvn test
