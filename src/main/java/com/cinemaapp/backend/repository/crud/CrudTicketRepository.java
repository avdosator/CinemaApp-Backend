package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudTicketRepository extends JpaRepository<TicketEntity, UUID> {
}
