package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.MovieService;
import com.cinemaapp.backend.service.PaymentService;
import com.cinemaapp.backend.service.PdfService;
import com.cinemaapp.backend.service.StripeService;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentConfirmationResponse;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StripeService stripeService;
    private final PdfService pdfService;
    private final MovieService movieService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, StripeService stripeService, PdfService pdfService,
                              MovieService movieService) {
        this.paymentRepository = paymentRepository;
        this.stripeService = stripeService;
        this.pdfService = pdfService;
        this.movieService = movieService;
    }

    @Transactional
    @Override
    public PaymentConfirmationResponse processReservationAndPayment(CreatePaymentRequest createPaymentRequest) {

        // Step 1: Retrieve and validate PaymentIntent from Stripe
        PaymentIntent paymentIntent = stripeService.retrievePaymentIntent(createPaymentRequest.getPaymentIntentId());
        if (!"succeeded".equals(paymentIntent.getStatus())) {
            throw new PaymentProcessingException("Payment was not successful");
        }

        // Step 2: Create all entities (reservation, seat reservation(s), payment and ticket)
        PaymentProcessingResult paymentProcessingResult = paymentRepository.processReservationAndPayment(createPaymentRequest);

        // Step 3: Generate ticket PDF
        Movie movie = movieService.findById(paymentProcessingResult.getReservation().getSeatReservations().get(0).getProjectionInstance().getProjection().getMovieId());
        ProjectionInstance projectionInstance = paymentProcessingResult.getReservation().getSeatReservations().get(0).getProjectionInstance();
        List<String> seatNumbers = paymentProcessingResult.getReservation()
                .getSeatReservations()
                .stream()
                .map(seatReservation -> seatReservation.getSeat().getNumber())
                .toList();

        byte[] ticketPdf = pdfService.generateTicket(
                movie.getTitle(),
                projectionInstance.getDate().toString(),
                projectionInstance.getTime(),
                projectionInstance.getProjection().getHall().getVenue().getName(),
                projectionInstance.getProjection().getHall().getName(),
                seatNumbers,
                paymentProcessingResult.getPayment().getAmount(),
                movie.getPgRating(),
                movie.getLanguage(),
                movie.getDurationInMinutes()
        );

        return new PaymentConfirmationResponse("success", "Reservation and payment successfully processed");
    }

    @Override
    public String createPaymentIntent(CreatePaymentIntentRequest createPaymentIntentRequest) {
        try {
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(
                    createPaymentIntentRequest.getAmount(),
                    createPaymentIntentRequest.getUserId(),
                    createPaymentIntentRequest.getProjectionInstanceId());
            return paymentIntent.getClientSecret();
        } catch (Exception e) {
            throw new PaymentProcessingException("Failed to create payment with Stripe", e);
        }
    }
}
