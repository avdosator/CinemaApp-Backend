package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.ResetCodeRequest;

public interface EmailService {

    void sendResetCode(ResetCodeRequest resetCodeRequest);

    void sendTicketAndReceipt(String to, String subject, String body, byte[] ticketPdf, byte[] receiptPdf);
}
