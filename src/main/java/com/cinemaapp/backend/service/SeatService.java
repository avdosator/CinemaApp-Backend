package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatService {

    List<Seat> getSeatsByHall(UUID hallId);
}
