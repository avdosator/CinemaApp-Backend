package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.service.domain.model.Photo;
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
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "entity_id")
    private UUID refEntityId;

    @Column(name = "entity_type")
    private String entityType;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getRefEntityId() {
        return refEntityId;
    }

    public void setRefEntityId(UUID refEntityId) {
        this.refEntityId = refEntityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
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

    public Photo toDomainModel() {
        return Photo.builder()
                .id(this.id)
                .url(this.url)
                .refEntityId(this.refEntityId)
                .entityType(this.entityType)
                //.createdAt(this.createdAt)
                //.updatedAt(this.updatedAt)
                .build();
    }
}
