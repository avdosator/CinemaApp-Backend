package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String passwordHash;
    private final String phone;
    private final City city;
    private final List<Payment> payments;
    private final List<Reservation> reservations;
    private final List<SeatReservation> seatReservations;
    private final String role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public User(UUID id, String firstName, String lastName, String email, String passwordHash, String phone, City city,
                List<Payment> payments, List<Reservation> reservations, List<SeatReservation> seatReservations,
                String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.city = city;
        this.payments = payments;
        this.reservations = reservations;
        this.seatReservations = seatReservations;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public City getCity() {
        return city;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<SeatReservation> getSeatReservations() {
        return seatReservations;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    private static class UserBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private String passwordHash;
        private String phone;
        private City city;
        private List<Payment> payments;
        private List<Reservation> reservations;
        private List<SeatReservation> seatReservations;
        private String role;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        UserBuilder() {
        }

        public UserBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder city(City city) {
            this.city = city;
            return this;
        }

        public UserBuilder payments(List<Payment> payments) {
            this.payments = payments;
            return this;
        }

        public UserBuilder reservations(List<Reservation> reservations) {
            this.reservations = reservations;
            return this;
        }

        public UserBuilder seatReservations(List<SeatReservation> seatReservations) {
            this.seatReservations = seatReservations;
            return this;
        }

        public UserBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(
                    this.id,
                    this.firstName,
                    this.lastName,
                    this.email,
                    this.passwordHash,
                    this.phone,
                    this.city,
                    this.payments,
                    this.reservations,
                    this.seatReservations,
                    this.role,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}


