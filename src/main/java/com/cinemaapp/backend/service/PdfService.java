package com.cinemaapp.backend.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

    public byte[] generateTicket(
            String movieName,
            String date,
            String time,
            String venue,
            String hall,
            List<String> seats,
            double totalPrice,
            String rating,
            String language,
            int duration
    ) {
        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                // Title
                contentStream.showText("Movie Ticket");
                contentStream.newLine();
                contentStream.newLine();

                // Booking Details Section
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                contentStream.showText("Booking Details");
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.showText(movieName);
                contentStream.newLine();
                contentStream.showText(date + " at " + time);
                contentStream.newLine();
                contentStream.showText(venue);
                contentStream.newLine();
                contentStream.showText(rating + " | " + language + " | " + duration);
                contentStream.newLine();
                contentStream.newLine();

                // Seats Details Section
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                contentStream.showText("Seats Details");
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.showText("Hall: " + hall);
                contentStream.newLine();
                contentStream.showText("Seat(s): " + String.join(", ", seats));
                contentStream.newLine();
                contentStream.showText("Total Price: " + totalPrice + " KM");
                contentStream.newLine();

                contentStream.endText();
            }
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error in generating PDF ticket", e);
        }
    }


}
