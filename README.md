# Project Management Tool

A Sodelic-inspired project management and SDLC workspace built with
**Java Spring Boot**, **Next.js**, and **PostgreSQL**.

The application helps manage projects from requirements through
features, tasks, milestones, and delivery tracking.

## Features

-   Project and task management
-   Requirement and feature management
-   Requirement-to-feature traceability
-   Kanban task board
-   Project phases and milestones
-   Change request and scope tracking
-   Project documentation and activity history
-   Project dashboard and health indicators

> This is an independent project inspired by common SDLC and
> project-management workflows.

## Technology Stack

### Backend

-   Java 21
-   Spring Boot
-   Spring Web
-   Spring Data JPA / Hibernate
-   Spring Security and JWT
-   PostgreSQL
-   Flyway
-   Springdoc OpenAPI

### Frontend

-   Next.js
-   TypeScript
-   Tailwind CSS
-   TanStack Query

## Project Structure

``` text
project-management-tool/
├── backend/              # Spring Boot REST API
├── frontend/             # Next.js application
├── infrastructure/       # Docker and infrastructure configuration
├── docs/                 # Architecture, database and API documentation
├── docker-compose.yml
└── README.md
```

## Prerequisites

Install the following before running the application:

-   Java 21
-   Maven 3.9 or later
-   PostgreSQL 13 or later
-   Node.js (LTS) and npm
-   Git

## Backend Setup

1.  Create a PostgreSQL database named `project_manager`.
2.  Open the `backend` directory.
3.  Configure the database connection using environment variables or
    `application.yml`.
4.  Start the Spring Boot application:

``` bash
mvn spring-boot:run
```

Flyway runs the database migrations automatically when the application
starts.

Example environment variables:

``` text
DB_URL=jdbc:postgresql://localhost:5432/project_manager
DB_USERNAME=postgres
DB_PASSWORD=your_password
SERVER_PORT=8080
```

Do not commit real credentials or secrets to source control.

## Frontend Setup

After the Next.js frontend has been created:

``` bash
cd frontend
npm install
npm run dev
```

The frontend development server normally runs at
`http://localhost:3000`.

Configure the frontend API base URL to point to the backend, for
example:

``` text
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/api
```

Adjust the URL to match the API configuration used by the application.

## Database Migrations

Flyway migration scripts are stored in:

``` text
backend/src/main/resources/db/migration/
```

Migration files follow Flyway's naming convention:

``` text
V1__create_users.sql
V2__create_projects.sql
V3__create_requirements.sql
```

After a migration has been applied to a database, do not edit that
migration. Create a new versioned migration instead.

## API Documentation

When Springdoc OpenAPI is enabled, Swagger UI is generally available at:

``` text
http://localhost:8080/swagger-ui/index.html
```

## Development Guidelines

-   Keep the backend as a **modular monolith**, organized by business
    domain.
-   Keep controllers focused on HTTP handling and place business logic
    in application services.
-   Use request and response DTOs instead of exposing JPA entities
    directly.
-   Use Flyway for all database schema changes.
-   Validate incoming requests and protect endpoints with Spring
    Security.
-   Add unit and integration tests for important business workflows.
-   Keep secrets and environment-specific configuration outside version
    control.

## Current Development Status

This project is under active development. Features, APIs, and database
structures may change as implementation progresses.

## License

Add a license before distributing or publishing this project. Until
then, all rights are reserved by the project owner.
