package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.SeatReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudSeatReservationRepository extends JpaRepository<SeatReservationEntity, UUID> {
}
