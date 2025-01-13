package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.model.TicketPrice;

import java.util.List;

public interface TicketPriceService {

    List<TicketPrice> findAll();
}
