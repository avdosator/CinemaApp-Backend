package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "seat")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SeatEntity {

    //  getters and setters

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
}
