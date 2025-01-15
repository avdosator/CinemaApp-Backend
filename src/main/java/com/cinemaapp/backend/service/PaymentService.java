package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentCreationResponse;

public interface PaymentService {

    PaymentCreationResponse processReservationAndPayment(CreatePaymentRequest createPaymentRequest);
    String createPaymentIntent(CreatePaymentIntentRequest createPaymentIntentRequest);
}
