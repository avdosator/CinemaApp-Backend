package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.SeatRepository;
import com.cinemaapp.backend.service.SeatService;
import com.cinemaapp.backend.service.domain.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getSeatsByHall(UUID hallId) {
        return seatRepository.getSeatsByHall(hallId);
    }
}
