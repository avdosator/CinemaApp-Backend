package com.cinemaapp.backend.repository;

import com.cinemaapp.backend.service.domain.model.Reservation;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;

public interface PaymentRepository {

    public Reservation createPayment(CreatePaymentRequest createPaymentRequest);
}
