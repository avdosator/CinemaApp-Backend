package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReservationEntity {

    // rel with   seat_reservation + getters and setters

    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "reservationEntity")
    private List<SeatReservationEntity> seatReservationEntities;

    @Column(name = "status")
    private String status; // should be "pending", "confirmed" or "canceled"

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
