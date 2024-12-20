package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.repository.crud.CrudPaymentRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentJpaRepository implements PaymentRepository {

    private final CrudPaymentRepository crudPaymentRepository;
    private final CrudUserRepository crudUserRepository;

    @Autowired
    public PaymentJpaRepository(CrudPaymentRepository crudPaymentRepository, CrudUserRepository crudUserRepository) {
        this.crudPaymentRepository = crudPaymentRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public String createPayment(CreatePaymentRequest createPaymentRequest) {
        return "";
    }
}
