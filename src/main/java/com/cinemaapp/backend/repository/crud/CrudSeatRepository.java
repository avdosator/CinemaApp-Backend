package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CrudSeatRepository extends JpaRepository<SeatEntity, UUID> {

    List<SeatEntity> findAllByHallEntity_Id(UUID hallId);
}
