package com.cinemaapp.backend.service;

import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;

import java.util.List;
import java.util.UUID;

public interface StripeService {

    PaymentIntent createPaymentIntent(double amount, UUID userId, UUID projectionInstanceId) throws Exception;

    PaymentIntent retrievePaymentIntent(String paymentIntentId);

    List<Charge> getChargesForPaymentIntent(String paymentIntentId);
}
