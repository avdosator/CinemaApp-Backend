package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.Seat;
import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "seat")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SeatEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id")
    private HallEntity hallEntity;

    @OneToMany(mappedBy = "seatEntity")
    private List<SeatReservationEntity> seatReservationEntities;

    @Column(name = "type")
    private String type; // should be "regular", "vip" or "love"

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public HallEntity getHallEntity() {
        return hallEntity;
    }

    public void setHallEntity(HallEntity hallEntity) {
        this.hallEntity = hallEntity;
    }

    public List<SeatReservationEntity> getSeatReservationEntities() {
        return seatReservationEntities;
    }

    public void setSeatReservationEntities(List<SeatReservationEntity> seatReservationEntities) {
        this.seatReservationEntities = seatReservationEntities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Seat toDomainModel() {
        List<SeatReservation> seatReservations = (this.seatReservationEntities == null ? Collections.emptyList() :
                this.seatReservationEntities.stream()
                        .map(SeatReservationEntity::toDomainModel)
                        .toList());

        return Seat.builder()
                .id(this.id)
                .number(this.number)
                .hall(this.hallEntity.toDomainModel())
                .seatReservations(seatReservations)
                .type(this.type)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
