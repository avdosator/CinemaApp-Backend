package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Seat {

    private final UUID id;
    private final String number;
    private final Hall hall;
    private final List<SeatReservation> seatReservations;
    private final String type;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Seat(UUID id, String number, Hall hall, List<SeatReservation> seatReservations, String type,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.number = number;
        this.hall = hall;
        this.seatReservations = seatReservations;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Hall getHall() {
        return hall;
    }

    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static SeatBuilder builder() {
        return new SeatBuilder();
    }

    public static class SeatBuilder {
        private UUID id;
        private String number;
        private Hall hall;
        private List<SeatReservation> seatReservations;
        private String type;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        SeatBuilder() {
        }

        public SeatBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public SeatBuilder number(String number) {
            this.number = number;
            return this;
        }

        public SeatBuilder hall(Hall hall) {
            this.hall = hall;
            return this;
        }

        public SeatBuilder seatReservations(List<SeatReservation> seatReservations) {
            this.seatReservations = seatReservations;
            return this;
        }

        public SeatBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SeatBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SeatBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Seat build() {
            return new Seat(
                    this.id,
                    this.number,
                    this.hall,
                    this.seatReservations,
                    this.type,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}

