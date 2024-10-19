package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudReservationRepository extends JpaRepository<ReservationEntity, UUID> {
}
