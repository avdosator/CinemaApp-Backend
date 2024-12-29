package com.cinemaapp.backend.service;

import java.util.List;

public interface PdfService {

    byte[] generateTicket(String movieName, String date, String time, String venue, String hall, List<String> seats, double totalPrice, String rating, String language, int duration);

    byte[] generateReceiptPdf(String receiptDetails);
}
