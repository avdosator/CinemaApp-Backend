package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Hall {

    private final UUID id;
    private final String name;
    private final Venue venue;
    private final List<Projection> projections;
    private final List<Seat> seats;
    private final int totalSeats;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Hall(UUID id, String name, Venue venue, List<Projection> projections, List<Seat> seats, int totalSeats, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.projections = projections;
        this.seats = seats;
        this.totalSeats = totalSeats;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Venue getVenue() {
        return venue;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static HallBuilder builder() {
        return new HallBuilder();
    }

    public static class HallBuilder {
        private UUID id;
        private String name;
        private Venue venue;
        private List<Projection> projections;
        private List<Seat> seats;
        private int totalSeats;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        HallBuilder() {}

        public HallBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public HallBuilder name(String name) {
            this.name = name;
            return this;
        }

        public HallBuilder venue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public HallBuilder projections(List<Projection> projections) {
            this.projections = projections;
            return this;
        }

        public HallBuilder seats(List<Seat> seats) {
            this.seats = seats;
            return this;
        }

        public HallBuilder totalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public HallBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public HallBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Hall build() {
            return new Hall(
                    this.id,
                    this.name,
                    this.venue,
                    this.projections,
                    this.seats,
                    this.totalSeats,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}

