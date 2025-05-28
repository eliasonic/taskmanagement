# Task Management API

A Spring Boot REST API for managing tasks, with Docker and PostgreSQL integration.

## Features
- CRUD operations for tasks
- Dockerized PostgreSQL database
- Swagger UI for API documentation
- Layered architecture following Spring best practices

## Prerequisites
- Java 21
- Maven
- Docker

## Setup

### Local Development
1. Start PostgreSQL database:
   ```bash
   docker-compose up -d postgres
   ```
2. Build and run the application
   ```bash
   mvn spring-boot:run
   ```
   
### Docker
1. Build and start all services:
   ```bash
   docker-compose up --build
   ```