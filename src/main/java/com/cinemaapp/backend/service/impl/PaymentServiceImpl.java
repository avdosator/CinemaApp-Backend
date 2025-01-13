package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.exception.HtmlExtractingException;
import com.cinemaapp.backend.exception.PaymentProcessingException;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.*;
import com.cinemaapp.backend.service.domain.model.Movie;
import com.cinemaapp.backend.service.domain.model.ProjectionInstance;
import com.cinemaapp.backend.service.domain.model.SeatReservation;
import com.cinemaapp.backend.service.domain.model.TicketPrice;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.request.EmailDetailsRequest;
import com.cinemaapp.backend.service.domain.request.PdfTicketRequest;
import com.cinemaapp.backend.service.domain.response.PaymentCreationResponse;
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
    private final ProjectionInstanceService projectionInstanceService;
    private final JsoupService jsoupService;
    private final SecurityServiceImpl securityService;
    private final TicketPriceService ticketPriceService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, StripeService stripeService, PdfService pdfService,
                              MovieService movieService, EmailService emailService, ProjectionInstanceService projectionInstanceService,
                              JsoupService jsoupService, SecurityServiceImpl securityService, TicketPriceService ticketPriceService) {
        this.paymentRepository = paymentRepository;
        this.stripeService = stripeService;
        this.pdfService = pdfService;
        this.movieService = movieService;
        this.emailService = emailService;
        this.projectionInstanceService = projectionInstanceService;
        this.jsoupService = jsoupService;
        this.securityService = securityService;
        this.ticketPriceService = ticketPriceService;
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
            byte[] ticketPdf = pdfService.generateTicket(
                    generatePdfTicketRequest(
                            movie,
                            paymentProcessingResult.getReservation().getSeatReservations(),
                            projectionInstance,
                            paymentProcessingResult.getPayment().getAmount())
            );

            // Step 4: Get receipt URL and create receipt PDF
            List<Charge> charges = stripeService.getChargesForPaymentIntent(createPaymentRequest.getPaymentIntentId());
            String receiptHtmlString = jsoupService.parseHtmlFromUrl(charges.get(0).getReceiptUrl());

            // Validate extracted content
            if (receiptHtmlString.isEmpty()) {
                throw new HtmlExtractingException("Extracted receipt content is empty! Cannot generate PDF.");
            }
            byte[] receiptPdf = pdfService.generateReceiptPdf(receiptHtmlString);

            // Step 5: Send receipt and ticket to user's email address
            sendTicketAndReceipt(ticketPdf, receiptPdf);

            return new PaymentCreationResponse("success", "Reservation and payment successfully processed");
        } catch (Exception e) {
            throw new PaymentProcessingException("An error occurred during payment processing", e);
        }
    }

    @Override
    public String createPaymentIntent(CreatePaymentIntentRequest createPaymentIntentRequest) {
        List<TicketPrice> ticketPrices = ticketPriceService.findAll();

        double totalAmount = createPaymentIntentRequest.getSelectedSeats().stream()
                .mapToDouble(seat -> {
                    TicketPrice ticketPrice = ticketPrices.stream()
                            .filter(price -> price.getSeatType().equalsIgnoreCase(seat.getType()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Invalid seat type: " + seat.getType()));
                    return ticketPrice.getPrice();
                })
                .sum();

        try {
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(
                    totalAmount / 2, // Divide by 2 because stripe will calculate it in euros
                    createPaymentIntentRequest.getUserId(),
                    createPaymentIntentRequest.getProjectionInstanceId());
            return paymentIntent.getClientSecret();
        } catch (Exception e) {
            throw new PaymentProcessingException("Failed to create payment with Stripe", e);
        }
    }

    private void sendTicketAndReceipt(byte[] ticketPdf, byte[] receiptPdf) {
        EmailDetailsRequest emailDetailsRequest = new EmailDetailsRequest();
        emailDetailsRequest.setTo(securityService.getCurrentUser().getEmail());
        emailDetailsRequest.setSubject("Movie Ticket and Receipt");
        emailDetailsRequest.setBody("""
                Hello,
                                
                Thank you for your purchase! We sent you movie ticket and payment receipt.
                                
                                
                CinemaApp
                """);
        emailService.sendTicketAndReceipt(emailDetailsRequest, ticketPdf, receiptPdf);
    }

    private PdfTicketRequest generatePdfTicketRequest(Movie movie,
                                                      List<SeatReservation> seatReservations,
                                                      ProjectionInstance projectionInstance,
                                                      double totalPrice) {
        List<String> seatNumbers = seatReservations
                .stream()
                .map(seatReservation -> seatReservation.getSeat().getNumber())
                .toList();

        PdfTicketRequest pdfTicketRequest = new PdfTicketRequest();
        pdfTicketRequest.setMovieName(movie.getTitle());
        pdfTicketRequest.setDate(projectionInstance.getDate().toString());
        pdfTicketRequest.setTime(projectionInstance.getTime());
        pdfTicketRequest.setVenueName(projectionInstance.getProjection().getHall().getVenue().getName());
        pdfTicketRequest.setHallName(projectionInstance.getProjection().getHall().getName());
        pdfTicketRequest.setSeats(seatNumbers);
        pdfTicketRequest.setTotalPrice(totalPrice);
        pdfTicketRequest.setPgRating(movie.getPgRating());
        pdfTicketRequest.setLanguage(movie.getLanguage());
        pdfTicketRequest.setDuration(movie.getDurationInMinutes());

        return pdfTicketRequest;
    }
}
