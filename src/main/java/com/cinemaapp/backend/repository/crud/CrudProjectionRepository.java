package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.ProjectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudProjectionRepository extends JpaRepository<ProjectionEntity, UUID> {
}
