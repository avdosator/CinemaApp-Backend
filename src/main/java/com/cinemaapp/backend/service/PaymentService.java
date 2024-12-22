package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.CreatePaymentResponse;

public interface PaymentService {

    CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);
    String createPaymentIntent(CreatePaymentIntentRequest createPaymentIntentRequest);
    //void handleFailedPayment(UUID paymentId);
}
