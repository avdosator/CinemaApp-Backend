package com.cinemaapp.backend.repository.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.repository.crud.*;
import com.cinemaapp.backend.repository.entity.*;
import com.cinemaapp.backend.service.domain.model.Payment;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PaymentJpaRepository implements PaymentRepository {

    private final CrudPaymentRepository crudPaymentRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudReservationRepository crudReservationRepository;
    private final CrudSeatRepository crudSeatRepository;
    private final CrudSeatReservationRepository crudSeatReservationRepository;
    private final CrudProjectionInstanceRepository crudProjectionInstanceRepository;
    private final CrudTicketRepository crudTicketRepository;

    @Autowired
    public PaymentJpaRepository(CrudPaymentRepository crudPaymentRepository, CrudUserRepository crudUserRepository,
                                CrudReservationRepository crudReservationRepository, CrudSeatRepository crudSeatRepository,
                                CrudSeatReservationRepository crudSeatReservationRepository,
                                CrudProjectionInstanceRepository crudProjectionInstanceRepository,
                                CrudTicketRepository crudTicketRepository) {
        this.crudPaymentRepository = crudPaymentRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudReservationRepository = crudReservationRepository;
        this.crudSeatRepository = crudSeatRepository;
        this.crudSeatReservationRepository = crudSeatReservationRepository;
        this.crudProjectionInstanceRepository = crudProjectionInstanceRepository;
        this.crudTicketRepository = crudTicketRepository;
    }

    @Override
    public Payment findById(UUID id) {
        PaymentEntity paymentEntity = crudPaymentRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Payment not found for ID: " + id));
        return paymentEntity.toDomainModel();
    }

    @Override
    public PaymentProcessingResult processReservationAndPayment(CreatePaymentRequest createPaymentRequest) {
        UserEntity userEntity = crudUserRepository.findById(createPaymentRequest.getUserId()).orElseThrow();
        ReservationEntity reservationEntity = createReservation(createPaymentRequest, userEntity);
        PaymentEntity paymentEntity = createPayment(createPaymentRequest, userEntity);
        TicketEntity ticketEntity = createTicket(paymentEntity, reservationEntity.getSeatReservationEntities());
        return new PaymentProcessingResult(
                reservationEntity.toDomainModel(),
                paymentEntity.toDomainModel(),
                ticketEntity.toDomainModel()
        );
    }

    private ReservationEntity createReservation(CreatePaymentRequest createPaymentRequest, UserEntity userEntity) {

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUserEntity(userEntity);
        reservationEntity.setStatus("confirmed");
        reservationEntity.setTotalPrice(createPaymentRequest.getAmount());
        reservationEntity.setCreatedAt(LocalDateTime.now());
        ReservationEntity savedReservation = crudReservationRepository.save(reservationEntity);
        reservationEntity.setSeatReservationEntities(createSeatReservations(savedReservation, createPaymentRequest, userEntity));
        return crudReservationRepository.save(reservationEntity);
    }

    private List<SeatReservationEntity> createSeatReservations(ReservationEntity reservationEntity, CreatePaymentRequest createPaymentRequest, UserEntity userEntity) {
        ProjectionInstanceEntity projectionInstanceEntity = crudProjectionInstanceRepository
                .findById(createPaymentRequest.getProjectionInstanceId()).orElseThrow();
        List<SeatReservationEntity> seatReservationEntities = new ArrayList<>();
        for (UUID seatId : createPaymentRequest.getSelectedSeats()) {
            SeatReservationEntity seatReservationEntity = new SeatReservationEntity();
            seatReservationEntity.setProjectionInstanceEntity(projectionInstanceEntity);
            seatReservationEntity.setSeatEntity(crudSeatRepository.findById(seatId).orElseThrow());
            seatReservationEntity.setReservationEntity(reservationEntity);
            seatReservationEntity.setStatus("purchased");
            seatReservationEntity.setCreatedAt(LocalDateTime.now());
            seatReservationEntity.setUserEntity(userEntity);
            seatReservationEntities.add(crudSeatReservationRepository.save(seatReservationEntity));
        }
        return seatReservationEntities;
    }

    private PaymentEntity createPayment(CreatePaymentRequest createPaymentRequest, UserEntity userEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(createPaymentRequest.getAmount());
        paymentEntity.setMethod("card"); // Placeholder; will refine after integrating Stripe.
        paymentEntity.setStatus("pending");
        paymentEntity.setPaymentTime(LocalDateTime.now());
        paymentEntity.setUserEntity(userEntity);
        paymentEntity.setUpdatedAt(LocalDateTime.now());
        return crudPaymentRepository.save(paymentEntity);
    }

    private TicketEntity createTicket(PaymentEntity paymentEntity, List<SeatReservationEntity> seatReservationEntities) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPaymentEntity(paymentEntity);
        ticketEntity.setSeatReservationEntities(seatReservationEntities);
        ticketEntity.setCreatedAt(LocalDateTime.now());
        return crudTicketRepository.save(ticketEntity);
    }
}
