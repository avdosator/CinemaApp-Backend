package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private final UUID id;
    private final double amount;
    private final String method;
    private final LocalDateTime paymentTime;
    private final String status;
    private final User user;
    private final Ticket ticket;
    private final LocalDateTime updatedAt;

    public Payment(UUID id, double amount, String method, LocalDateTime paymentTime, String status, User user,
                   Ticket ticket, LocalDateTime updatedAt) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.paymentTime = paymentTime;
        this.status = status;
        this.user = user;
        this.ticket = ticket;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    private static class PaymentBuilder {
        private UUID id;
        private double amount;
        private String method;
        private LocalDateTime paymentTime;
        private String status;
        private User user;
        private Ticket ticket;
        private LocalDateTime updatedAt;

        PaymentBuilder() {
        }

        public PaymentBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder method(String method) {
            this.method = method;
            return this;
        }

        public PaymentBuilder paymentTime(LocalDateTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

        public PaymentBuilder status(String status) {
            this.status = status;
            return this;
        }

        public PaymentBuilder user(User user) {
            this.user = user;
            return this;
        }

        public PaymentBuilder ticket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public PaymentBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Payment build() {
            return new Payment(
                    this.id,
                    this.amount,
                    this.method,
                    this.paymentTime,
                    this.status,
                    this.user,
                    this.ticket,
                    this.updatedAt
            );
        }
    }
}
