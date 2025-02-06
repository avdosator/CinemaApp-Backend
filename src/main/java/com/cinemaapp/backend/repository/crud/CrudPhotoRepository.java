package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CrudPhotoRepository extends JpaRepository<PhotoEntity, UUID> {
    List<PhotoEntity> findByRefEntityId(UUID entityId);

    PhotoEntity findPhotoByRefEntityId(UUID entityId);

    @Modifying
    @Transactional
    void deleteFirstByRefEntityId(UUID entityId); // Designed for updating venues photo, when new photo is added, delete previous one

    List<PhotoEntity> findAllByRefEntityIdIn(List<UUID> venueIds);
}
