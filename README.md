# CinemaApp - Backend

CinemaApp is a web application designed to facilitate online movie ticket purchases for a movie companies around the country. This repository contains the backend code, which provides the necessary services for ticket purchasing, seat reservations, and projection schedule management.

## Project Overview

This is the backend part of the CinemaApp, built using **Spring Boot** and **PostgreSQL**. It provides RESTful services to support the frontend operations, including ticketing, seat reservations, and movie schedule management.

## Dependencies

- **Spring Boot**: The foundation for the backend application.
- **Spring Data JPA**: For data persistence and interaction with the PostgreSQL database.
- **PostgreSQL**: The relational database used for storing data.
- **Swagger**: For API documentation.

## Planned Features

- **Ticket Purchase API**: Endpoints for purchasing tickets.
- **Seat Reservation API**: Endpoints for reserving specific seats in the cinema.
- **Projection Schedule API**: Endpoints to view movie schedules across multiple cinema subsidiaries.

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

   
## Database Configuration

The application uses **PostgreSQL**. You should have **PostgreSQL** installed on your device.
