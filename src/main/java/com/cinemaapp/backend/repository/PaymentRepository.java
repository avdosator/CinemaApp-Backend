package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;

public interface PaymentRepository {

    public String createPayment(CreatePaymentRequest createPaymentRequest);
}
