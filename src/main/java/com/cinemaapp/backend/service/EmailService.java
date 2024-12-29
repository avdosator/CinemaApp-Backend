package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.ResetCodeRequest;

public interface EmailService {

    String sendResetCode(ResetCodeRequest resetCodeRequest);

    String sendTicketAndReceipt(String to, String subject, String body, byte[] ticketPdf, byte[] receiptPdf);
}
