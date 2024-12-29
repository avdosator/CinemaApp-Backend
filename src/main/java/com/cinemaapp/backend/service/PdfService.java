package com.cinemaapp.backend.service;

import com.cinemaapp.backend.service.domain.request.PdfTicketRequest;

public interface PdfService {

    byte[] generateTicket(PdfTicketRequest pdfTicketRequest);

    byte[] generateReceiptPdf(String receiptDetails);
}
