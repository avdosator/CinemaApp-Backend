package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.repository.PaymentRepository;
import com.cinemaapp.backend.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        paymentService = new PaymentServiceImpl(
                paymentRepository, stripeService, pdfService, movieService, emailService, projectionInstanceService, jsoupService);
    }

    @Test
    void processReservationAndPayment() {


    }
}