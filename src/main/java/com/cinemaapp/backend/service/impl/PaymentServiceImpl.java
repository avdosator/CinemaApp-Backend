package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.PaymentService;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    @Override
    public String createPayment(CreatePaymentRequest createPaymentRequest) {
        paymentRepository.createPayment(createPaymentRequest);
        return "";
    }
}
