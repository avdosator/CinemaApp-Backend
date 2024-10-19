package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TicketEntity {

    // implement relationship with seat_reservation

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity paymentEntity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
