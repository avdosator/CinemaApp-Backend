package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectionInstance {

    private final UUID id;
    private final Projection projection;
    private final List<SeatReservation> seatReservations;
    private final LocalDate date;
    private final String time;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProjectionInstance(UUID id, Projection projection, List<SeatReservation> seatReservations, LocalDate date,
                              String time, LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
        this.id = id;
        this.projection = projection;
        this.seatReservations = seatReservations;
        this.date = date;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Projection getProjection() {
        return projection;
    }

    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ProjectionInstanceBuilder builder() {
        return new ProjectionInstanceBuilder();
    }

    public static class ProjectionInstanceBuilder {
        private UUID id;
        private Projection projection;
        private List<SeatReservation> seatReservations;
        private LocalDate date;
        private String time;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        ProjectionInstanceBuilder() {
        }

        public ProjectionInstanceBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ProjectionInstanceBuilder projection(Projection projection) {
            this.projection = projection;
            return this;
        }

        public ProjectionInstanceBuilder seatReservations(List<SeatReservation> seatReservations) {
            this.seatReservations = seatReservations;
            return this;
        }

        public ProjectionInstanceBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ProjectionInstanceBuilder time(String time) {
            this.time = time;
            return this;
        }

        public ProjectionInstanceBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ProjectionInstanceBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProjectionInstance build() {
            return new ProjectionInstance(
                    this.id,
                    this.projection,
                    this.seatReservations,
                    this.date,
                    this.time,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
