package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.TicketPriceRepository;
import com.cinemaapp.backend.service.TicketPriceService;
import com.cinemaapp.backend.service.domain.model.TicketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriceServiceImpl implements TicketPriceService {

    private final TicketPriceRepository ticketPriceRepository;

    @Autowired
    public TicketPriceServiceImpl(TicketPriceRepository ticketPriceRepository) {
        this.ticketPriceRepository = ticketPriceRepository;
    }

    @Override
    public List<TicketPrice> findAll() {
        return ticketPriceRepository.findAll();
    }
}
