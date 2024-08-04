# Redis Project

This project is a Redis-based application that performs CRUD (Create, Read, Update, Delete) operations for user data and caches the results to improve performance.

## Features

- Redis for caching
- Docker for Redis image
- RESTful APIs with Spring Boot Web
- Database integration with Spring Data JPA and PostgreSQL
- ModelMapper for object mapping

## Technologies
* Java 17
* Spring Boot 3.0.5
    * Redis
    * JPA Repository
    * Lombok
    * Model Mapper
* PostgreSQL
* Open API
* Docker

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 14+
* Maven 3+

To build and run the project, follow these steps:

* Clone the repository: `https://github.com/azizCan10/redis.git`
* Navigate to the project directory
* Open a db in PostgreSQL called redis
* Open your redis image using docker
* in application.yml file, set your own values

* Build the project: `mvn clean install`
* Run the project: `mvn spring-boot:run`

-> The application will be available at http://localhost:8080

-> Swagger will be available at http://localhost:8080/swagger-ui/index.html

-> You can find collection file in collection folder