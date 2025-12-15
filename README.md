

---

```md
# Product Catalog Service

A simple and clean **Product Catalog REST API** built with **Spring Boot** following **Clean Architecture** principles.

## Features

- Create, update, delete products
- Activate / deactivate products
- Pagination & sorting support
- Clean RESTful endpoints
- DTO-based request/response design
- Validation with Spring Validation
- Layered architecture (Controller / Service / Repository)

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- JUnit 5

## Project Structure



com.demo.catalog
├── controller
├── service
│   └── impl
├── repository
├── entity
├── dto
│   ├── request
│   └── response
├── mapper
└── exception```

````

## Configuration

Edit `application.yml` file:

```yml
spring:
  datasource:
    url: jdbc:postgresql://host:port/catalog
    username: username
    password: password
````



## Run the Application

```bash
mvn spring-boot:run
```

## Testing

Basic context load test included.
Postman collection can be used for API testing.

## Purpose

This project demonstrates:

* Clean REST API design
* Proper layering and separation of concerns
* DTO usage and validation
* Real-world backend development practices

---

```
RexGm
