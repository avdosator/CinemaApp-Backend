package com.cinemaapp.backend.service.domain.response;

public class PaymentConfirmationResponse {

    private String status;
    private String message;

    public PaymentConfirmationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
