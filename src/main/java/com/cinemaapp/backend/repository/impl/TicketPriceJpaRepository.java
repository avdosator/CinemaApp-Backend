package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.TicketPriceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TicketPriceJpaRepository implements TicketPriceRepository {
    @Override
    public List<TicketPrice> findAll() {
        return null;
    }
}
