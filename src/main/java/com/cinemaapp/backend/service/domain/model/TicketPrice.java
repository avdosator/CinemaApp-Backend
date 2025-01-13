package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TicketPrice {

    private final UUID id;
    private final String seatType;
    private final double price;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TicketPrice(UUID id, String seatType, double price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.seatType = seatType;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static TicketPriceBuilder builder() {
        return new TicketPriceBuilder();
    }

    public static class TicketPriceBuilder {
        private UUID id;
        private String seatType;
        private double price;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        TicketPriceBuilder() {
        }

        public TicketPriceBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public TicketPriceBuilder seatType(String seatType) {
            this.seatType = seatType;
            return this;
        }

        public TicketPriceBuilder price(double price) {
            this.price = price;
            return this;
        }

        public TicketPriceBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TicketPriceBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public TicketPrice build() {
            return new TicketPrice(
                    this.id,
                    this.seatType,
                    this.price,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
