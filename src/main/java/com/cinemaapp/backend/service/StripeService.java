package com.cinemaapp.backend.service;

import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeListParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class StripeService {

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

    public PaymentIntent retrievePaymentIntent(String paymentIntentId) {
        try {
            return PaymentIntent.retrieve(paymentIntentId);
        } catch (Exception e) {
            throw new PaymentProcessingException("Failed to retrieve PaymentIntent with ID: " + paymentIntentId, e);
        }
    }

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

    public byte[] downloadReceipt(String receiptUrl) {
        try {
            URL url = new URL(receiptUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            try (InputStream inputStream = connection.getInputStream();
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to download receipt from Stripe", e);
        }
    }
}
