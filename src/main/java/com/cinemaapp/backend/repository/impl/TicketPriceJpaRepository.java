package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.TicketPriceRepository;
import com.cinemaapp.backend.repository.crud.CrudTicketPriceRepository;
import com.cinemaapp.backend.repository.entity.TicketPriceEntity;
import com.cinemaapp.backend.service.domain.model.TicketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketPriceJpaRepository implements TicketPriceRepository {

    private final CrudTicketPriceRepository crudTicketPriceRepository;

    @Autowired
    public TicketPriceJpaRepository(CrudTicketPriceRepository crudTicketPriceRepository) {
        this.crudTicketPriceRepository = crudTicketPriceRepository;
    }

    @Override
    public List<TicketPrice> findAll() {
        List<TicketPriceEntity> ticketPriceEntities = crudTicketPriceRepository.findAll();
        List<TicketPrice> ticketPrices = new ArrayList<>();
        for (TicketPriceEntity ticketPriceEntity : ticketPriceEntities) {
            ticketPrices.add(ticketPriceEntity.toDomainModel());
        }
        return ticketPrices;
    }
}
