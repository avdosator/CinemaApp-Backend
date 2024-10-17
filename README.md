# CinemaApp - Backend

CinemaApp is a web application designed to facilitate online movie ticket purchases for a movie companies around the country. This repository contains the backend code, which provides the necessary services for ticket purchasing, seat reservations, and projection schedule management.

## Project Overview

This is the backend part of the CinemaApp, built using **Spring Boot** and **PostgreSQL**. It provides RESTful services to support the frontend operations, including ticketing, seat reservations, and movie schedule management.

## Dependencies

- **Spring Boot**: The foundation for the backend application.
- **Spring Data JPA**: For data persistence and interaction with the PostgreSQL database.
- **PostgreSQL**: The relational database used for storing data.
- **Swagger**: For API documentation.

## Setup and Installation

1. Clone the backend repository:
   ```
   git clone https://github.com/avdosator/CinemaApp-Backend
   ```

2. Navigate to the project directory:
   ```
   cd CinemaApp-backend
   ```

3. Install dependencies and build the project:
   ```
   ./mvnw clean install
   ```

4. Run the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```
   
5. Access the API documentation (Swagger) at:
   ```
   http://localhost:8080/swagger-ui/
   ```

   
## Database Configuration

[View the ERD for CinemaApp](https://lucid.app/lucidchart/ff698fb4-95d1-4291-be81-691cc1dfa8c9/edit?viewport_loc=-20946%2C-1764%2C3087%2C1505%2C0_0&invitationId=inv_cd50e08d-ba3a-4f88-a7c6-723c7d996581)

The application uses **PostgreSQL**. You should have **PostgreSQL** installed on your device.
