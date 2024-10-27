package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Reservation {

    private final UUID id;
    private final User user;
    private final List<SeatReservation> seatReservations;
    private final String status;
    private final double totalPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Reservation(UUID id, User user, List<SeatReservation> seatReservations, String status, double totalPrice,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.seatReservations = seatReservations;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    private static class ReservationBuilder {
        private UUID id;
        private User user;
        private List<SeatReservation> seatReservations;
        private String status;
        private double totalPrice;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ReservationBuilder() {
        }

        public ReservationBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ReservationBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ReservationBuilder seatReservations(List<SeatReservation> seatReservations) {
            this.seatReservations = seatReservations;
            return this;
        }

        public ReservationBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ReservationBuilder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public ReservationBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ReservationBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Reservation build() {
            return new Reservation(
                    this.id,
                    this.user,
                    this.seatReservations,
                    this.status,
                    this.totalPrice,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
