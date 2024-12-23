package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.*;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentConfirmationResponse;
import com.cinemaapp.backend.utils.UserUtils;
import com.stripe.model.Charge;
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
    private final EmailService emailService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, StripeService stripeService, PdfService pdfService,
                              MovieService movieService, EmailService emailService) {
        this.paymentRepository = paymentRepository;
        this.stripeService = stripeService;
        this.pdfService = pdfService;
        this.movieService = movieService;
        this.emailService = emailService;
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

        // Step 3: Get receipt URL and download receipt PDF
        List<Charge> charges = stripeService.getChargesForPaymentIntent(createPaymentRequest.getPaymentIntentId());
        String receiptUrl = charges.get(0).getReceiptUrl();
        byte[] receiptPdf = stripeService.downloadReceipt(receiptUrl);

        emailService.sendTicketAndReceipt(
                UserUtils.getCurrentUser().getEmail(),
                "Your Movie Ticket and Receipt", // Simple subject
                """
                        Hello,
                                    
                        Thank you for your purchase! Attached are your movie ticket and payment receipt.
                                                                        
                        CinemaApp
                        """,
                ticketPdf,
                receiptPdf
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
