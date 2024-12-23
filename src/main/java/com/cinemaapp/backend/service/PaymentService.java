package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentConfirmationResponse;

public interface PaymentService {

    PaymentConfirmationResponse processReservationAndPayment(CreatePaymentRequest createPaymentRequest);
    String createPaymentIntent(CreatePaymentIntentRequest createPaymentIntentRequest);
    //void handleFailedPayment(UUID paymentId);
}
