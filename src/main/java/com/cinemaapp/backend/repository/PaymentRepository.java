package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Payment;
import com.cinemaapp.backend.service.domain.model.Reservation;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;

import java.util.UUID;

public interface PaymentRepository {

    public Reservation createPayment(CreatePaymentRequest createPaymentRequest);
    public Payment findById(UUID id);
}
