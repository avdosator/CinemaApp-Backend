package com.cinemaapp.backend.repository.crud;

import com.cinemaapp.backend.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CrudPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
