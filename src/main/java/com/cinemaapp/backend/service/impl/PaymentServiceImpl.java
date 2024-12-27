package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.*;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.response.PaymentCreationResponse;
import com.cinemaapp.backend.utils.UserUtils;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StripeService stripeService;
    private final PdfService pdfService;
    private final MovieService movieService;
    private final EmailService emailService;
    private final ProjectionInstanceService projectionInstanceService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, StripeService stripeService, PdfService pdfService,
                              MovieService movieService, EmailService emailService, ProjectionInstanceService projectionInstanceService) {
        this.paymentRepository = paymentRepository;
        this.stripeService = stripeService;
        this.pdfService = pdfService;
        this.movieService = movieService;
        this.emailService = emailService;
        this.projectionInstanceService = projectionInstanceService;
    }

    @Transactional
    @Override
    public PaymentCreationResponse processReservationAndPayment(CreatePaymentRequest createPaymentRequest) {

        // Step 1: Retrieve and validate PaymentIntent from Stripe
        PaymentIntent paymentIntent = stripeService.retrievePaymentIntent(createPaymentRequest.getPaymentIntentId());
        if (!"succeeded".equals(paymentIntent.getStatus())) {
            throw new PaymentProcessingException("Payment was not successful");
        }

        try {
            // Step 2: Create all entities (reservation, seat reservation(s), payment and ticket)
            PaymentProcessingResult paymentProcessingResult = paymentRepository.processReservationAndPayment(createPaymentRequest);

            // Step 3: Generate ticket PDF
            Movie movie = movieService.findById(createPaymentRequest.getMovieId());
            ProjectionInstance projectionInstance = projectionInstanceService.findById(createPaymentRequest.getProjectionInstanceId());
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

            // Step 4: Get receipt URL and create receipt PDF
            List<Charge> charges = stripeService.getChargesForPaymentIntent(createPaymentRequest.getPaymentIntentId());
            String receiptUrl = charges.get(0).getReceiptUrl();
            String receiptHtmlString = JsoupService.parseHtmlFromUrl(receiptUrl);

            // Validate extracted content
            if (receiptHtmlString.isEmpty()) {
                throw new RuntimeException("Extracted receipt content is empty! Cannot generate PDF.");
            }
            byte[] receiptPdf = pdfService.generateReceiptPdf(receiptHtmlString);
            Files.write(Path.of("test_receipt.pdf"), receiptPdf); // Save for testing

            // Step 5: Send receipt and ticket to user's email address
            emailService.sendTicketAndReceipt(
                    UserUtils.getCurrentUser().getEmail(),
                    "Your Movie Ticket and Receipt",
                    """
                            Hello,
                                        
                            Thank you for your purchase! We sent you your movie ticket and payment receipt.
                                           
                                                                            
                            CinemaApp
                            """,
                    ticketPdf,
                    receiptPdf
            );
            return new PaymentCreationResponse("success", "Reservation and payment successfully processed");
        } catch (Exception e) {
            throw new PaymentProcessingException("An error occurred during payment processing", e);
        }
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
