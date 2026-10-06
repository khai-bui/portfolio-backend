# Portfolio Backend API

Backend REST API for managing personal portfolio data including profile, projects, and skills.
## Live Demo

- Swagger UI: https://portfolio-backend-production-bae31.up.railway.app/swagger-ui.html
- OpenAPI Docs: https://portfolio-backend-production-bae31.up.railway.app/v3/api-docs

## Project Highlights

- RESTful API built with Spring Boot
- PostgreSQL persistence with Spring Data JPA
- JWT authentication and role-based authorization
- Public read APIs and ADMIN-protected write APIs
- DTO validation and global exception handling
- Swagger/OpenAPI documentation
- Unit and security testing with JUnit, Mockito and MockMvc
- Dockerized application with Docker Compose
- CI pipeline with GitHub Actions
- Deployed on Railway with managed PostgreSQL
## Tech Stack

- Java 25
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- PostgreSQL
- Swagger / OpenAPI
- Docker & Docker Compose
- JUnit
- Mockito

## Features

- Public portfolio APIs
- Profile management
- Project management
- Skill management
- DTO validation
- Global exception handling
- JWT authentication
- Role-based authorization
- Admin-only create, update and delete operations
- Swagger API documentation
- Unit and security tests
- Dockerized deployment

## Security

Public:

- `GET /api/profile/**`
- `GET /api/projects/**`
- `GET /api/skills/**`
- `POST /api/auth/login`

Admin only:

- `POST /api/**`
- `PUT /api/**`
- `DELETE /api/**`

## Run with Docker

Create a `.env` file:

```env
DB_PASSWORD=your_password
JWT_SECRET=your_secret
ADMIN_USERNAME=admin
ADMIN_PASSWORD=your_admin_password

# Portfolio Backend API

A RESTful backend API for managing a personal developer portfolio.

The application provides public APIs for displaying profile information,
projects, and technical skills, while administrative operations are protected
using JWT authentication and role-based authorization.

## Tech Stack

- Java 25
- Spring Boot 4
- Spring Data JPA
- Spring Security
- JWT Authentication
- PostgreSQL
- Swagger / OpenAPI
- Docker & Docker Compose
- JUnit
- Mockito
- GitHub Actions

## Architecture

```mermaid
flowchart TD
    Client[Client / Swagger / Frontend]

    Client --> Controller

    Controller --> Validation[DTO + Validation]
    Validation --> Service
    Service --> Repository
    Repository --> DB[(PostgreSQL)]

    Client --> Security[Spring Security]
    Security --> JWT[JWT Authentication]
    JWT --> Controller
