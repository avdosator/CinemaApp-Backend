package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.CreatePaymentResponse;

public interface PaymentService {

    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);
}
