package com.cinemaapp.backend.service.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Photo {

    private final UUID id;
    private final String url;
    private final UUID refEntityId;
    private final String entityType;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Photo(UUID id, String url, UUID refEntityId, String entityType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.url = url;
        this.refEntityId = refEntityId;
        this.entityType = entityType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public UUID getRefEntityId() {
        return refEntityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static PhotoBuilder builder() {
        return new PhotoBuilder();
    }

    public static class PhotoBuilder {
        private UUID id;
        private String url;
        private UUID refEntityId;
        private String entityType;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public PhotoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PhotoBuilder url(String url) {
            this.url = url;
            return this;
        }

        public PhotoBuilder refEntityId(UUID refEntityId) {
            this.refEntityId = refEntityId;
            return this;
        }

        public PhotoBuilder entityType(String entityType) {
            this.entityType = entityType;
            return this;
        }

        public PhotoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PhotoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Photo build() {
            return new Photo(
                    this.id,
                    this.url,
                    this.refEntityId,
                    this.entityType,
                    this.createdAt,
                    this.updatedAt
            );
        }
    }
}
