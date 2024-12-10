package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Projection {

    private final UUID id;
    private final Hall hall;
    private final UUID movieId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String[] startTime;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Projection(UUID id, Hall hall, UUID movieId, LocalDate startDate, LocalDate endDate, String[] startTime,
                      String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.hall = hall;
        this.movieId = movieId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Hall getHall() {
        return hall;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String[] getStartTime() {
        return startTime;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ProjectionBuilder builder() {
        return new ProjectionBuilder();
    }

    public static class ProjectionBuilder {
        private UUID id;
        private Hall hall;
        private UUID movieId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String[] startTime;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        ProjectionBuilder() {
        }

        public ProjectionBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ProjectionBuilder hall(Hall hall) {
            this.hall = hall;
            return this;
        }

        public ProjectionBuilder movie(UUID movieId) {
            this.movieId = movieId;
            return this;
        }

        public ProjectionBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public ProjectionBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public ProjectionBuilder startTime(String[] startTime) {
            this.startTime = startTime;
            return this;
        }

        public ProjectionBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ProjectionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ProjectionBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Projection build() {
            return new Projection(
                    this.id,
                    this.hall,
                    this.movieId,
                    this.startDate,
                    this.endDate,
                    this.startTime,
                    this.status,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }

}

