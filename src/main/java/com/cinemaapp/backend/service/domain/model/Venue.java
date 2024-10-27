package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Venue {

    private final UUID id;
    private final String name;
    private final String street;
    private final String streetNumber;
    private final City city;
    private final List<Hall> halls;
    private final String phone;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Venue(UUID id, String name, String street, String streetNumber, City city, List<Hall> halls, String phone, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.halls = halls;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public City getCity() {
        return city;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static VenueBuilder builder() {
        return new VenueBuilder();
    }

    public static class VenueBuilder {
        private UUID id;
        private String name;
        private String street;
        private String streetNumber;
        private City city;
        private List<Hall> halls;
        private String phone;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        VenueBuilder() {
        }

        public VenueBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public VenueBuilder name(String name) {
            this.name = name;
            return this;
        }

        public VenueBuilder street(String street) {
            this.street = street;
            return this;
        }

        public VenueBuilder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public VenueBuilder city(City city) {
            this.city = city;
            return this;
        }

        public VenueBuilder halls(List<Hall> halls) {
            this.halls = halls;
            return this;
        }

        public VenueBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public VenueBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public VenueBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Venue build() {
            return new Venue(
                    this.id,
                    this.name,
                    this.street,
                    this.streetNumber,
                    this.city,
                    this.halls,
                    this.phone,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
