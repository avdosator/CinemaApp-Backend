package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.SeatRepository;
import com.cinemaapp.backend.repository.crud.CrudSeatRepository;
import com.cinemaapp.backend.repository.entity.SeatEntity;
import com.cinemaapp.backend.service.domain.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SeatJpaRepository implements SeatRepository {

    private final CrudSeatRepository crudSeatRepository;

    @Autowired
    public SeatJpaRepository(CrudSeatRepository crudSeatRepository) {
        this.crudSeatRepository = crudSeatRepository;
    }

    @Override
    public List<Seat> getSeatsByHall(UUID hallId) {
        List<SeatEntity> seatEntities = crudSeatRepository.findAllByHallEntity_Id(hallId);
        List<Seat> seats = new ArrayList<>();
        for (SeatEntity seatEntity : seatEntities) {
            seats.add(seatEntity.toDomainModel());
        }
        return seats;
    }
}
