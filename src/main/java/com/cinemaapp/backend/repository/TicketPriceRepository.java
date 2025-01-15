package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.TicketPrice;

import java.util.List;

public interface TicketPriceRepository {
    List<TicketPrice> findAll();
}
