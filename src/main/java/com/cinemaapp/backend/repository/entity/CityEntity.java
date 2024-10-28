package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.City;
import com.cinemaapp.backend.service.domain.model.User;
import com.cinemaapp.backend.service.domain.model.Venue;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "city")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CityEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private int postalCode;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "cityEntity", cascade = CascadeType.ALL)
    private List<UserEntity> userEntities;

    @OneToMany(mappedBy = "cityEntity")
    private List<VenueEntity> venueEntities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public List<VenueEntity> getVenueEntities() {
        return venueEntities;
    }

    public void setVenueEntities(List<VenueEntity> venueEntities) {
        this.venueEntities = venueEntities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // add missing after UserEntity and VenueEntity toDomainModel implementation
    public City toDomainModel() {
        List<User> users = (this.userEntities == null ? Collections.emptyList() : this.userEntities.stream()
                .map(UserEntity::toDomainModel)
                .toList());
        List<Venue> venues = (this.venueEntities == null ? Collections.emptyList() : this.venueEntities.stream()
                .map(VenueEntity::toDomainModel)
                .toList());

        return City.builder()
                .id(this.id)
                .name(this.name)
                .postalCode(this.postalCode)
                .country(this.country)
                .users(users)
                .venues(venues)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}