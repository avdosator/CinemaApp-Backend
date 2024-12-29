package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.EmailDetailsRequest;

public interface EmailService {

    void sendResetCode(EmailDetailsRequest emailDetailsRequest);

    void sendTicketAndReceipt(EmailDetailsRequest emailDetailsRequest, byte[] ticketPdf, byte[] receiptPdf);
}
