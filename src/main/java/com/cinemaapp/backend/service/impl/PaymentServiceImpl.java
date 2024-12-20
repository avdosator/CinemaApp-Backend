package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.PaymentService;
import com.cinemaapp.backend.service.domain.model.Reservation;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.CreatePaymentResponse;
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
    public CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        Reservation reservation =  paymentRepository.createPayment(createPaymentRequest);
        CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse();
        createPaymentResponse.setReservationId(reservation.getId());
        createPaymentResponse.setPaymentStatus("pending");
        createPaymentResponse.setTotalPrice(reservation.getTotalPrice());
        return createPaymentResponse;
    }
}
