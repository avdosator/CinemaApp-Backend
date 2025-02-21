package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projection_instance")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectionInstanceEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "projection_id", referencedColumnName = "id")
    private ProjectionEntity projectionEntity;

    @OneToMany(mappedBy = "projectionInstanceEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SeatReservationEntity> seatReservationEntities = new ArrayList<>();

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private String time;

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

    public ProjectionEntity getProjectionEntity() {
        return projectionEntity;
    }

    public void setProjectionEntity(ProjectionEntity projectionEntity) {
        this.projectionEntity = projectionEntity;
    }

    public List<SeatReservationEntity> getSeatReservationEntities() {
        return seatReservationEntities;
    }

    public void setSeatReservationEntities(List<SeatReservationEntity> seatReservationEntities) {
        this.seatReservationEntities = seatReservationEntities;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public ProjectionInstance toDomainModel() {

        List<SeatReservation> seatReservations = (this.seatReservationEntities == null ?
                Collections.emptyList() : this.seatReservationEntities.stream()
                .map(SeatReservationEntity::toDomainModel)
                .toList());

        return ProjectionInstance.builder()
                .id(this.id)
                .projectionId(this.projectionEntity.getId())
                .seatReservations(seatReservations)
                .date(this.date)
                .time(this.time)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
