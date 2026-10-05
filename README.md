# Portfolio Backend API

Backend REST API for managing personal portfolio data including profile, projects, and skills.

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
