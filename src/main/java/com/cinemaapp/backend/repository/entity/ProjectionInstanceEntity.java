package com.cinemaapp.backend.repository.entity;

import com.cinemaapp.backend.utils.SeatsStatusConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
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

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "seats_status", columnDefinition = "jsonb", nullable = false)
    @Convert(converter = SeatsStatusConverter.class) // Custom converter for JSONB mapping
    private Map<String, String> seatsStatus;

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

    public Map<String, String> getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(Map<String, String> seatsStatus) {
        this.seatsStatus = seatsStatus;
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
}
