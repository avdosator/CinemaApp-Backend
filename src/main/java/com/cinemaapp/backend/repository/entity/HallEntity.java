package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.Hall;
import com.cinemaapp.backend.service.domain.model.Projection;
import com.cinemaapp.backend.service.domain.model.Seat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hall")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HallEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id")
    private VenueEntity venueEntity;

    @OneToMany(mappedBy = "hallEntity", cascade = CascadeType.MERGE)
    private List<ProjectionEntity> projectionEntities;

    @OneToMany(mappedBy = "hallEntity")
    private List<SeatEntity> seatEntities;

    @Column(name = "total_seats")
    private int totalSeats;

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

    public VenueEntity getVenueEntity() {
        return venueEntity;
    }

    public void setVenueEntity(VenueEntity venueEntity) {
        this.venueEntity = venueEntity;
    }

    public List<ProjectionEntity> getProjectionEntities() {
        return projectionEntities;
    }

    public void setProjectionEntities(List<ProjectionEntity> projectionEntities) {
        this.projectionEntities = projectionEntities;
    }

    public List<SeatEntity> getSeatEntities() {
        return seatEntities;
    }

    public void setSeatEntities(List<SeatEntity> seatEntities) {
        this.seatEntities = seatEntities;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
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

    public Hall toDomainModel() {
        List<Projection> projections = (this.projectionEntities == null ?
                Collections.emptyList() : this.projectionEntities.stream()
                .map(ProjectionEntity::toDomainModel)
                .toList());
        List<Seat> seats = (this.projectionEntities == null ? Collections.emptyList() : this.seatEntities.stream()
                .map(SeatEntity::toDomainModel)
                .toList());

        return Hall.builder()
                .id(this.id)
                .name(this.name)
                .venue(this.venueEntity.toDomainModel())
                .projections(projections)
                //.seats(seats)
                //.totalSeats(this.totalSeats)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
