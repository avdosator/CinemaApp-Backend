package com.cinemaapp.backend.service;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StripeService {

    public PaymentIntent createPaymentIntent(double amount, UUID userId, UUID projectionInstanceId ) throws Exception {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100)) // Amount in cents
                .setCurrency("EUR")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .putMetadata("userId", userId.toString())
                .putMetadata("projectionInstanceId", projectionInstanceId.toString())
                .build();

        return PaymentIntent.create(params);
    }
}
