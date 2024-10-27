package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.cinemaapp.backend.service.domain.model.Ticket;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TicketEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity paymentEntity;

    @OneToMany(mappedBy = "ticketEntity")
    private List<SeatReservationEntity> seatReservationEntities;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
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

    public Ticket toDomainModel() {
        List<SeatReservation> seatReservations = (this.seatReservationEntities == null ? Collections.emptyList() :
                this.seatReservationEntities.stream()
                        .map(SeatReservationEntity::toDomainModel)
                        .toList());

        return Ticket.builder()
                .id(this.id)
                .payment(this.paymentEntity.toDomainModel())
                .seatReservations(seatReservations)
                .createdAt(this.createdAt)
                .build();
    }
}
