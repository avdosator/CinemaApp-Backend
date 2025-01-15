package com.cinemaapp.backend.controller.dto;

import com.cinemaapp.backend.service.domain.model.Payment;
import com.cinemaapp.backend.service.domain.model.Reservation;
import com.cinemaapp.backend.service.domain.model.Ticket;

public class PaymentProcessingResult {

    private final Reservation reservation;
    private final Payment payment;
    private final Ticket ticket;

    public PaymentProcessingResult(Reservation reservation, Payment payment, Ticket ticket) {
        this.reservation = reservation;
        this.payment = payment;
        this.ticket = ticket;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Payment getPayment() {
        return payment;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
