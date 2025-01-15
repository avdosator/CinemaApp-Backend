package com.cinemaapp.backend.utils;

import com.cinemaapp.backend.service.domain.model.Seat;
import com.cinemaapp.backend.service.domain.model.TicketPrice;

import java.util.List;

public class PaymentAmountCalculator {

    public static double calculateTotalAmount(List<Seat> selectedSeats, List<TicketPrice> ticketPrices) {
        return selectedSeats.stream()
                .mapToDouble(seat -> {
                    TicketPrice ticketPrice = ticketPrices.stream()
                            .filter(price -> price.getSeatType().equalsIgnoreCase(seat.getType()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid seat type: " + seat.getType()));
                    return ticketPrice.getPrice();
                })
                .sum();
    }
}
