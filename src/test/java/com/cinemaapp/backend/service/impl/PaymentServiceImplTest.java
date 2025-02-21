package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.controller.dto.PaymentProcessingResult;
import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.*;
import com.cinemaapp.backend.service.domain.model.*;
import com.cinemaapp.backend.service.domain.request.CreatePaymentIntentRequest;
import com.cinemaapp.backend.service.domain.request.CreatePaymentRequest;
import com.cinemaapp.backend.service.domain.request.EmailDetailsRequest;
import com.cinemaapp.backend.service.domain.request.PdfTicketRequest;
import com.cinemaapp.backend.service.domain.response.PaymentCreationResponse;
import com.cinemaapp.backend.utils.PaymentAmountCalculator;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private StripeService stripeService;
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PdfService pdfService;

    @Mock
    private JsoupService jsoupService;

    @Mock
    private EmailService emailService;

    @Mock
    private MovieService movieService;

    @Mock
    private ProjectionInstanceService projectionInstanceService;

    @Mock
    private SecurityServiceImpl securityService;

    @Mock
    private TicketPriceService ticketPriceService;

    @Mock
    private ProjectionService projectionService;

    @Mock
    private PaymentAmountCalculator paymentAmountCalculator;

    private PaymentService paymentService;


    @BeforeEach
    public void setUp() {
        paymentService = new PaymentServiceImpl(
                paymentRepository, stripeService, pdfService, movieService, emailService,
                projectionInstanceService, jsoupService, securityService, ticketPriceService, projectionService);
    }

    @Test
    void createPaymentIntent() throws Exception {
        CreatePaymentIntentRequest createPaymentIntentRequest = buildCreatePaymentIntentRequest();

        // Mock Ticket Prices
        Mockito.when(ticketPriceService.findAll()).thenReturn(buildTicketPrices());

        // Calculate expected amount
        double totalAmount = PaymentAmountCalculator.calculateTotalAmount(createPaymentIntentRequest.getSelectedSeats(), buildTicketPrices());
        double expectedAmount = totalAmount / 2; // Match the division by 2 in the service

        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setAmount((long) totalAmount);
        paymentIntent.setClientSecret("clientSecret");

        // Mock Stripe Service
        Mockito.when(stripeService.createPaymentIntent(expectedAmount, createPaymentIntentRequest.getUserId(), createPaymentIntentRequest.getProjectionInstanceId()))
                .thenReturn(paymentIntent);

        // Execute the service method
        String clientSecret = paymentService.createPaymentIntent(createPaymentIntentRequest);

        // Verify the method call
        Mockito.verify(stripeService, times(1)).createPaymentIntent(
                expectedAmount,
                createPaymentIntentRequest.getUserId(),
                createPaymentIntentRequest.getProjectionInstanceId()
        );

        // Assertions
        Assertions.assertEquals("clientSecret", clientSecret);
        Assertions.assertEquals(41, paymentIntent.getAmount());
    }

    private List<TicketPrice> buildTicketPrices() {
        return List.of(
                TicketPrice.builder().seatType("regular").price(7).build(),
                TicketPrice.builder().seatType("vip").price(10).build(),
                TicketPrice.builder().seatType("love").price(24).build()
        );
    }

    private CreatePaymentIntentRequest buildCreatePaymentIntentRequest() {
        CreatePaymentIntentRequest createPaymentIntentRequest = new CreatePaymentIntentRequest();
        createPaymentIntentRequest.setSelectedSeats(buildSelectedSeats());
        createPaymentIntentRequest.setUserId(UUID.randomUUID());
        createPaymentIntentRequest.setProjectionInstanceId(UUID.randomUUID());
        return createPaymentIntentRequest;
    }

    private List<Seat> buildSelectedSeats() {
        return List.of(
                Seat.builder().number("A1").type("regular").build(),
                Seat.builder().number("I2").type("love").build(),
                Seat.builder().number("G1").type("vip").build()
        );
    }

    @Test
    void processReservationAndPayment() throws IOException {
        User user = new User(UUID.randomUUID(), null, null, "test@gmail.com", null, null, null, null, null, null);
        CreatePaymentRequest request = buildPaymentRequest(user.getId());

        PaymentIntent paymentIntent = buildPaymentIntent(request.getPaymentIntentId());
        Mockito.when(stripeService.retrievePaymentIntent(request.getPaymentIntentId()))
                .thenReturn(paymentIntent);

        PaymentProcessingResult paymentProcessingResult = buildPaymentProcessingResult(user);
        Mockito.when(paymentRepository.processReservationAndPayment(request))
                .thenReturn(paymentProcessingResult);

        Movie movie = buildMovie(request.getMovieId());
        Mockito.when(movieService.findById(request.getMovieId()))
                .thenReturn(movie);

        Projection projection = buildProjection(buildHall(buildVenue()));
        Mockito.when(projectionService.findById(projection.getId())).thenReturn(projection);

        ProjectionInstance projectionInstance = buildProjectionInstance(request.getProjectionInstanceId(), projection.getId());
        Mockito.when(projectionInstanceService.findById(request.getProjectionInstanceId())).thenReturn(projectionInstance);


        byte[] ticketPdf = buildTicketPdf();
        ArgumentCaptor<PdfTicketRequest> pdfTicketRequestArgumentCaptor = ArgumentCaptor.forClass(PdfTicketRequest.class);
        Mockito.when(pdfService.generateTicket(pdfTicketRequestArgumentCaptor.capture()))
                .thenReturn(ticketPdf);

        List<Charge> charges = buildCharges();
        Mockito.when(stripeService.getChargesForPaymentIntent(paymentIntent.getId()))
                .thenReturn(charges);

        String receiptHtmlString = "receiptHtmlString";
        Mockito.when(jsoupService.parseHtmlFromUrl(charges.get(0).getReceiptUrl()))
                .thenReturn(receiptHtmlString);

        byte[] receiptPdf = buildReceiptPdf();
        Mockito.when(pdfService.generateReceiptPdf(receiptHtmlString))
                .thenReturn(receiptPdf);

        Mockito.when(securityService.getCurrentUser())
                .thenReturn(user);

        ArgumentCaptor<EmailDetailsRequest> emailDetailsRequestArgumentCaptor = ArgumentCaptor.forClass(EmailDetailsRequest.class);
        Mockito.doNothing().when(emailService).sendTicketAndReceipt(emailDetailsRequestArgumentCaptor.capture(), Mockito.eq(ticketPdf), Mockito.eq(receiptPdf));

        PaymentCreationResponse paymentCreationResponse = paymentService.processReservationAndPayment(request);

        Mockito.verify(emailService, times(1)).sendTicketAndReceipt(any(), any(), any());
        Assertions.assertNotNull(paymentCreationResponse);
        Assertions.assertEquals("success", paymentCreationResponse.getStatus());

        PdfTicketRequest pdfTicketRequest = pdfTicketRequestArgumentCaptor.getValue();
        Assertions.assertEquals("Avatar", pdfTicketRequest.getMovieName());
        Assertions.assertEquals(20, pdfTicketRequest.getTotalPrice());
        Assertions.assertEquals("English", pdfTicketRequest.getLanguage());

        EmailDetailsRequest emailDetailsRequest = emailDetailsRequestArgumentCaptor.getValue();
        Assertions.assertEquals("test@gmail.com", emailDetailsRequest.getTo());
    }

    private ProjectionInstance buildProjectionInstance(UUID projectionInstanceId, UUID projectionId) {
        Venue venue = buildVenue();
        Hall hall = buildHall(venue);
        Projection projection = buildProjection(hall);
        return ProjectionInstance.builder()
                .id(projectionInstanceId)
                .date(LocalDate.now())
                .time("14:00")
                .projectionId(projectionId)
                .build();
    }

    private Projection buildProjection(Hall hall) {
        return Projection.builder().id(UUID.randomUUID()).hall(hall).build();
    }

    private Hall buildHall(Venue venue) {
        return Hall.builder().venue(venue).name("Hall 1").build();
    }

    private Venue buildVenue() {
        return Venue.builder().name("Deluxe Cinema").build();
    }

    private Movie buildMovie(UUID id) {
        return Movie.builder()
                .id(id)
                .durationInMinutes(120)
                .pgRating("PG-13")
                .language("English")
                .title("Avatar")
                .build();
    }

    private byte[] buildReceiptPdf() {
        return new byte[1024];
    }

    private List<Charge> buildCharges() {
        Charge charge = new Charge();
        charge.setReceiptUrl("testUrl");
        return List.of(charge);
    }

    private byte[] buildTicketPdf() {
        return new byte[200];
    }

    private CreatePaymentRequest buildPaymentRequest(UUID userId) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        // Seat seat = new Seat(null, "H1", null, null, "regular", null, null);
        request.setPaymentIntentId("paymentIntentId");
        request.setSelectedSeats(buildSelectedSeats());
        request.setMovieId(UUID.randomUUID());
        request.setProjectionInstanceId(UUID.randomUUID());
        request.setUserId(userId);
        return request;
    }

    private PaymentProcessingResult buildPaymentProcessingResult(User user) {
        Seat seat = buildSeat();
        List<SeatReservation> seatReservations = buildSeatReservations(seat);
        Reservation reservation = buildReservation(seatReservations);
        Payment payment = new Payment(UUID.randomUUID(), 20, null, null, null, user, null, reservation.getId(), null);
        Ticket ticket = new Ticket(UUID.randomUUID(), payment, seatReservations, null);
        PaymentProcessingResult result = new PaymentProcessingResult(reservation, payment, ticket);
        return result;
    }

    private Reservation buildReservation(List<SeatReservation> seatReservations) {
        return Reservation.builder().seatReservations(seatReservations).build();
    }

    private List<SeatReservation> buildSeatReservations(Seat seat) {
        return List.of(SeatReservation.builder().seat(seat).build());
    }

    private Seat buildSeat() {
        return Seat.builder().number("A1").type("regular").build();
    }

    private PaymentIntent buildPaymentIntent(String paymentIntendId) {
        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setId(paymentIntendId);
        paymentIntent.setStatus("succeeded");
        return paymentIntent;
    }
}