package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Ticket {

    private final UUID id;
    private final Payment payment;
    private final List<SeatReservation> seatReservations;
    private final LocalDateTime createdAt;

    public Ticket(UUID id, Payment payment, List<SeatReservation> seatReservations, LocalDateTime createdAt) {
        this.id = id;
        this.payment = payment;
        this.seatReservations = seatReservations;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }
    public Payment getPayment() {
        return payment;
    }
    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    private static class TicketBuilder {
        private UUID id;
        private Payment payment;
        private List<SeatReservation> seatReservations;
        private LocalDateTime createdAt;

        TicketBuilder() {}

        public TicketBuilder id(UUID id) {
            this.id = id;
            return this;
        }
        public TicketBuilder payment(Payment payment) {
            this.payment = payment;
            return this;
        }
        public TicketBuilder seatReservations(List<SeatReservation> seatReservations) {
            this.seatReservations = seatReservations;
            return this;
        }
        public TicketBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Ticket build() {
            return new Ticket(
                    this.id,
                    this.payment,
                    this.seatReservations,
                    this.createdAt
            );
        }
    }
}

