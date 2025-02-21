package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.exception.EmailSendingException;
import com.cinemaapp.backend.service.EmailService;
import com.cinemaapp.backend.service.domain.request.EmailDetailsRequest;
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
    public void sendResetCode(EmailDetailsRequest emailDetailsRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(emailSender);
            helper.setTo(emailDetailsRequest.getTo());
            helper.setSubject(emailDetailsRequest.getSubject());
            helper.setText(emailDetailsRequest.getBody(), true);
            javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            throw new EmailSendingException(e.getMessage(), e);
        }
    }

    @Override
    public void sendTicketAndReceipt(EmailDetailsRequest emailDetailsRequest, byte[] ticketPdf, byte[] receiptPdf) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // Enable multipart for attachments
            helper.setFrom(emailSender);
            helper.setTo(emailDetailsRequest.getTo());
            helper.setSubject(emailDetailsRequest.getSubject());
            helper.setText(emailDetailsRequest.getBody(), true);

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
            throw new EmailSendingException(e.getMessage(), e);
        }
    }
}
