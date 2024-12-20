package com.cinemaapp.backend.controller;

import com.cinemaapp.backend.service.PaymentService;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String createPayment(@Valid @RequestBody CreatePaymentRequest createPaymentRequest) {
        paymentService.createPayment(createPaymentRequest);
        return "";
    }
}
