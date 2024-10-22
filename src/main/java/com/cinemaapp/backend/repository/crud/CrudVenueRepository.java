package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudVenueRepository extends JpaRepository<VenueEntity, UUID> {
}
