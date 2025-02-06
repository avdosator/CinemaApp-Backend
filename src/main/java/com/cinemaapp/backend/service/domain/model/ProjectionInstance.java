package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProjectionInstance {

    private final UUID id;
    private final UUID projectionId;
    private final List<SeatReservation> seatReservations;
    private final LocalDate date;
    private final String time;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProjectionInstance(UUID id, UUID projectionId, List<SeatReservation> seatReservations, LocalDate date,
                              String time, LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
        this.id = id;
        this.projectionId = projectionId;
        this.seatReservations = seatReservations;
        this.date = date;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectionId() {
        return projectionId;
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
        private UUID projectionId;
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

        public ProjectionInstanceBuilder projectionId(UUID projectionId) {
            this.projectionId = projectionId;
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
                    this.projectionId,
                    this.seatReservations,
                    this.date,
                    this.time,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
