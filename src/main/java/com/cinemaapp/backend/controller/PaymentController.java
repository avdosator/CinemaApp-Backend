package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.PaymentService;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentCreationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@Valid @RequestBody CreatePaymentIntentRequest createPaymentIntentRequest) {
        String secret = paymentService.createPaymentIntent(createPaymentIntentRequest);
        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", secret);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public PaymentCreationResponse createPayment(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
        return paymentService.processReservationAndPayment(createPaymentRequest);
    }
}
