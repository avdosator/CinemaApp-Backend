package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.ProjectionInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudProjectionInstanceRepository extends JpaRepository<ProjectionInstanceEntity, UUID> {
}
