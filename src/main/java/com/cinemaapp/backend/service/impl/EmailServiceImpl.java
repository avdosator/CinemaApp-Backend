package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.EmailService;
import com.cinemaapp.backend.service.domain.request.ResetCodeRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private final JavaMailSender javaMailSender;

    @Override
    public void sendResetCode(ResetCodeRequest resetCodeRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(emailSender);
            helper.setTo(resetCodeRequest.getTo());
            helper.setSubject(resetCodeRequest.getSubject());
            helper.setText(resetCodeRequest.getBody(), true);
            javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendTicketAndReceipt(String to, String subject, String body, byte[] ticketPdf, byte[] receiptPdf) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // Enable multipart for attachments
            helper.setFrom(emailSender);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            // Attach Ticket PDF
            if (ticketPdf != null) {
                helper.addAttachment("Ticket.pdf", new ByteArrayResource(ticketPdf));
            }

            // Attach Receipt PDF
            if (receiptPdf != null) {
                helper.addAttachment("Receipt.pdf", new ByteArrayResource(receiptPdf));
            }

            javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
