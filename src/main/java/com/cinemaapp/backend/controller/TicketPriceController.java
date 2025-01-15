package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.TicketPriceService;
import com.cinemaapp.backend.service.domain.model.TicketPrice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket-prices")
@Tag(name = "Ticket Prices", description = "Endpoints for managing ticket prices")
public class TicketPriceController {

    private final TicketPriceService ticketPriceService;

    @Autowired
    public TicketPriceController(TicketPriceService ticketPriceService) {
        this.ticketPriceService = ticketPriceService;
    }

    @Operation(summary = "Get all ticket prices", description = "Retrieve a list of all ticket prices")
    @GetMapping
    public List<TicketPrice> getAllTicketPrices() {
        return ticketPriceService.findAll();
    }
}
