package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;

public interface PaymentService {

    public String createPayment(CreatePaymentRequest createPaymentRequest);
}
