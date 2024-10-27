package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.Projection;
import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projection")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private HallEntity hallEntity;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private MovieEntity movieEntity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private String[] startTime;  // It will represent start times of all projections for specific hall/venue -> "19:00"

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "status")
    private String status; //  should be "upcoming", "active", "completed" or "canceled"

    @OneToMany(mappedBy = "projectionEntity")
    private List<SeatReservationEntity> seatReservationEntities;

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

    public HallEntity getHallEntity() {
        return hallEntity;
    }

    public void setHallEntity(HallEntity hallEntity) {
        this.hallEntity = hallEntity;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String[] getStartTime() {
        return startTime;
    }

    public void setStartTime(String[] startTime) {
        this.startTime = startTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SeatReservationEntity> getSeatReservationEntities() {
        return seatReservationEntities;
    }

    public void setSeatReservationEntities(List<SeatReservationEntity> seatReservationEntities) {
        this.seatReservationEntities = seatReservationEntities;
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

    public Projection toDomainModel() {
        List<SeatReservation> seatReservations = (this.seatReservationEntities == null ? Collections.emptyList() :
                this.seatReservationEntities.stream()
                        .map(SeatReservationEntity::toDomainModel)
                        .toList());

        return Projection.builder()
                .id(this.id)
                .hall(this.hallEntity.toDomainModel())
                .movie(this.movieEntity.toDomainModel())
                .startDate(this.startDate)
                .endDate(this.endDate)
                .startTime(this.startTime)
                .availableSeats(this.availableSeats)
                .status(this.status)
                .seatReservations(seatReservations)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
