package com.cinemaapp.backend.service;

public interface EmailService {

    String sendResetCode(String to, String subject, String body);

    String sendTicketAndReceipt(String to, String subject, String body, byte[] ticketPdf, byte[] receiptPdf);
}
