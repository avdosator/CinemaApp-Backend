package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "seat_reservation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SeatReservationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "projection_instance_id", referencedColumnName = "id")
    private ProjectionInstanceEntity projectionInstanceEntity;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private SeatEntity seatEntity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private TicketEntity ticketEntity;

    @ManyToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private ReservationEntity reservationEntity;

    @Column(name = "status")
    private String status; // reserved or purchased

    @Column(name = "reservation_time")
    private LocalDateTime reservationTime;

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

    public ProjectionInstanceEntity getProjectionInstanceEntity() {
        return projectionInstanceEntity;
    }

    public void setProjectionInstanceEntity(ProjectionInstanceEntity projectionInstanceEntity) {
        this.projectionInstanceEntity = projectionInstanceEntity;
    }

    public SeatEntity getSeatEntity() {
        return seatEntity;
    }

    public void setSeatEntity(SeatEntity seatEntity) {
        this.seatEntity = seatEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TicketEntity getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
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

    public SeatReservation toDomainModel() {
        return SeatReservation.builder()
                .id(this.id)
                //.projectionInstance(this.projectionInstanceEntity.toDomainModel())
                .seat(this.seatEntity.toDomainModel())
                .user(this.userEntity.toDomainModel())
                //.ticket(this.ticketEntity.toDomainModel())
                //.reservation(this.reservationEntity.toDomainModel())
                .status(this.status)
                .reservationTime(this.reservationTime)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
