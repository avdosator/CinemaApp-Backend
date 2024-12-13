package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.SeatService;
import com.cinemaapp.backend.service.domain.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/seats")
@ControllerAdvice
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping()
    public List<Seat> getSeatsByHall(@RequestParam("hall") UUID hallId) {
        return seatService.getSeatsByHall(hallId);
    }
}
