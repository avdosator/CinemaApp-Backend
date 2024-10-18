package com.cinemaapp.backend.repository.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "photo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PhotoEntity {

    @Id
    @Column(name = "photo_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "entity_id")
    private UUID refEntityId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "is_cover")
    private boolean isCover;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
