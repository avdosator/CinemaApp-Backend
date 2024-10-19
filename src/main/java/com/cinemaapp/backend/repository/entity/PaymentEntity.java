package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PaymentEntity {

    // Add getters and setters


    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "method")
    private String method; // should be some enum DDL

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    @Column(name = "status")
    private String status; // also enum based on DDL

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "projection_id", referencedColumnName = "id")
    private ProjectionEntity projectionEntity;

    @OneToOne(mappedBy = "paymentEntity")
    private TicketEntity ticketEntity;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
