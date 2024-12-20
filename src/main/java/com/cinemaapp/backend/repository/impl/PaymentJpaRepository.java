package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.repository.crud.CrudPaymentRepository;
import com.cinemaapp.backend.repository.crud.CrudReservationRepository;
import com.cinemaapp.backend.repository.crud.CrudUserRepository;
import com.cinemaapp.backend.repository.entity.ReservationEntity;
import com.cinemaapp.backend.repository.entity.SeatReservationEntity;
import com.cinemaapp.backend.repository.entity.UserEntity;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PaymentJpaRepository implements PaymentRepository {

    private final CrudPaymentRepository crudPaymentRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudReservationRepository crudReservationRepository;

    @Autowired
    public PaymentJpaRepository(CrudPaymentRepository crudPaymentRepository, CrudUserRepository crudUserRepository,
                                CrudReservationRepository crudReservationRepository) {
        this.crudPaymentRepository = crudPaymentRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudReservationRepository = crudReservationRepository;
    }

    @Override
    public String createPayment(CreatePaymentRequest createPaymentRequest) {

        UserEntity userEntity = crudUserRepository.findById(createPaymentRequest.getUserId()).orElseThrow();
        ReservationEntity reservationEntity = createReservation(createPaymentRequest, userEntity);
        return "";
    }

    private ReservationEntity createReservation(CreatePaymentRequest createPaymentRequest, UserEntity userEntity) {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUserEntity(userEntity);
        reservationEntity.setStatus("confirmed");
        reservationEntity.setTotalPrice(createPaymentRequest.getAmount());
        reservationEntity.setSeatReservationEntities(createSeatReservations());
        reservationEntity.setCreatedAt(LocalDateTime.now());
        return crudReservationRepository.save(reservationEntity);
    }

    
}
