package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudSeatRepository extends JpaRepository<SeatEntity, UUID> {
}
