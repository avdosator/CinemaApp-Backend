package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.TicketPriceRepository;
import com.cinemaapp.backend.service.domain.model.TicketPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketPriceJpaRepository implements TicketPriceRepository {

    @Override
    public List<TicketPrice> findAll() {
        return null;
    }
}
