package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class City {

    private final UUID id;
    private final String name;
    private final int postalCode;
    private final String country;
    private final List<User> users;
    private final List<Venue> venues;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public City(UUID id, String name, int postalCode, String country, List<User> users, List<Venue> venues, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
        this.country = country;
        this.users = users;
        this.venues = venues;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static CityBuilder builder() {
        return new CityBuilder();
    }

    public static class CityBuilder {
        private UUID id;
        private String name;
        private int postalCode;
        private String country;
        private List<User> users;
        private List<Venue> venues;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        CityBuilder() {}

        public CityBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder postalCode(int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public CityBuilder country(String country) {
            this.country = country;
            return this;
        }

        public CityBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public CityBuilder venues(List<Venue> venues) {
            this.venues = venues;
            return this;
        }

        public CityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CityBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public City build() {
            return new City(
                    this.id,
                    this.name,
                    this.postalCode,
                    this.country,
                    this.users,
                    this.venues,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
