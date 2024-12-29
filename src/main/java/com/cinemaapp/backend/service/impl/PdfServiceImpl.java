package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.PdfService;
import com.cinemaapp.backend.service.domain.request.PdfTicketRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] generateTicket(PdfTicketRequest pdfTicketRequest) {
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
                contentStream.showText(pdfTicketRequest.getMovieName());
                contentStream.newLine();
                contentStream.showText(pdfTicketRequest.getDate() + " at " + pdfTicketRequest.getTime());
                contentStream.newLine();
                contentStream.showText(pdfTicketRequest.getVenueName());
                contentStream.newLine();
                contentStream.showText(
                        pdfTicketRequest.getPgRating() + " | " +
                                pdfTicketRequest.getLanguage() + " | " +
                                pdfTicketRequest.getDuration()
                );
                contentStream.newLine();
                contentStream.newLine();

                // Seats Details Section
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                contentStream.showText("Seats Details");
                contentStream.newLine();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.showText("Hall: " + pdfTicketRequest.getHallName());
                contentStream.newLine();
                contentStream.showText("Seat(s): " + String.join(", ", pdfTicketRequest.getSeats()));
                contentStream.newLine();
                contentStream.showText("Total Price: " + pdfTicketRequest.getTotalPrice() + " KM");
                contentStream.newLine();

                contentStream.endText();
            }
            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error in generating PDF ticket", e);
        }
    }

    // Convert receipt HTML to PDF file
    @Override
    public byte[] generateReceiptPdf(String receiptDetails) {

        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage();
            document.addPage(page);

            float margin = 50; // Margin from the page edges
            float yStart = 750; // Start position for text
            float lineHeight = 20; // Height of each line
            float yPosition = yStart;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            try {
                // Define fonts
                PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font regularFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                // Title
                contentStream.setFont(titleFont, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Receipt");
                contentStream.endText();
                yPosition -= 40; // Add space after title

                // Sanitize receipt details
                String sanitizedReceiptDetails = receiptDetails.replaceAll("[^\\x20-\\x7E]", " ");

                // Define sections for formatting
                String[] sections = sanitizedReceiptDetails.split("\n");
                for (String section : sections) {
                    if (section.trim().isEmpty()) continue; // Skip empty lines

                    // Wrap and write text
                    List<String> wrappedLines = wrapText(section, 90); // Adjust width as needed
                    for (String line : wrappedLines) {
                        if (yPosition < margin) {
                            // Add new page if content exceeds page height
                            contentStream.close();
                            page = new PDPage();
                            document.addPage(page);
                            contentStream = new PDPageContentStream(document, page);
                            yPosition = yStart;
                        }
                        contentStream.beginText();
                        contentStream.newLineAtOffset(margin, yPosition);
                        contentStream.setFont(regularFont, 12);
                        contentStream.showText(line.trim());
                        contentStream.endText();
                        yPosition -= lineHeight;
                    }

                    yPosition -= 10; // Add extra space between sections
                }
            } finally {
                contentStream.close();
            }

            document.save(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error generating PDF receipt", e);
        }
    }

    private List<String> wrapText(String text, int maxCharsPerLine) {
        List<String> wrappedLines = new ArrayList<>();
        while (text.length() > maxCharsPerLine) {
            int breakPoint = text.lastIndexOf(' ', maxCharsPerLine);
            if (breakPoint == -1) breakPoint = maxCharsPerLine; // No spaces, force break
            wrappedLines.add(text.substring(0, breakPoint));
            text = text.substring(breakPoint).trim();
        }
        wrappedLines.add(text); // Add the remaining text
        return wrappedLines;
    }
}
