package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.TicketPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CrudTicketPriceRepository extends JpaRepository<TicketPriceEntity, UUID> {
    List<TicketPriceEntity> findAll();
}
