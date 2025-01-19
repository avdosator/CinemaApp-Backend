package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class SeatReservation {

    private final UUID id;
    private final ProjectionInstance projectionInstance;
    private final Seat seat;
    private final User user;
    private final Ticket ticket;
    private final Reservation reservation;
    private final String status;
    private final LocalDateTime reservationTime;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SeatReservation(UUID id, ProjectionInstance projectionInstance, Seat seat, User user, Ticket ticket,
                           Reservation reservation, String status, LocalDateTime reservationTime, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.projectionInstance = projectionInstance;
        this.seat = seat;
        this.user = user;
        this.ticket = ticket;
        this.reservation = reservation;
        this.status = status;
        this.reservationTime = reservationTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public ProjectionInstance getProjectionInstance() {
        return projectionInstance;
    }

    public Seat getSeat() {
        return seat;
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static SeatReservationBuilder builder() {
        return new SeatReservationBuilder();
    }

    public static class SeatReservationBuilder {
        private UUID id;
        private ProjectionInstance projectionInstance;
        private Seat seat;
        private User user;
        private Ticket ticket;
        private Reservation reservation;
        private String status;
        private LocalDateTime reservationTime;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public SeatReservationBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public SeatReservationBuilder projectionInstance(ProjectionInstance projectionInstance) {
            this.projectionInstance = projectionInstance;
            return this;
        }

        public SeatReservationBuilder seat(Seat seat) {
            this.seat = seat;
            return this;
        }

        public SeatReservationBuilder user(User user) {
            this.user = user;
            return this;
        }

        public SeatReservationBuilder ticket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public SeatReservationBuilder reservation(Reservation reservation) {
            this.reservation = reservation;
            return this;
        }

        public SeatReservationBuilder status(String status) {
            this.status = status;
            return this;
        }

        public SeatReservationBuilder reservationTime(LocalDateTime reservationTime) {
            this.reservationTime = reservationTime;
            return this;
        }

        public SeatReservationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SeatReservationBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public SeatReservation build() {
            return new SeatReservation(
                    this.id,
                    this.projectionInstance,
                    this.seat,
                    this.user,
                    this.ticket,
                    this.reservation,
                    this.status,
                    this.reservationTime,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
