package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatRepository {

    List<Seat> getSeatsByHall(UUID hallId);
}
