package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.service.domain.model.Payment;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;

import java.util.UUID;

public interface PaymentRepository {

    public PaymentProcessingResult processReservationAndPayment(CreatePaymentRequest createPaymentRequest);
    public Payment findById(UUID id);
}
