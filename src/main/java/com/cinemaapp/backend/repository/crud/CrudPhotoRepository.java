package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CrudPhotoRepository extends JpaRepository<PhotoEntity, UUID> {
    List<PhotoEntity> findByRefEntityId(UUID entityId);
}
