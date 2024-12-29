package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.cinemaapp.backend.service.StripeService;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeListParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public PaymentIntent createPaymentIntent(double amount, UUID userId, UUID projectionInstanceId) throws Exception {
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

    @Override
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) {
        try {
            return PaymentIntent.retrieve(paymentIntentId);
        } catch (Exception e) {
            throw new PaymentProcessingException("Failed to retrieve PaymentIntent with ID: " + paymentIntentId, e);
        }
    }

    @Override
    public List<Charge> getChargesForPaymentIntent(String paymentIntentId) {
        try {
            ChargeListParams params = ChargeListParams.builder()
                    .setPaymentIntent(paymentIntentId)
                    .build();

            return Charge.list(params).getData(); // Fetch charges related to the PaymentIntent
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve charges for PaymentIntent", e);
        }
    }
}
