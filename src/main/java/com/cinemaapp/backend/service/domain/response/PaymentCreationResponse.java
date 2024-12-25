package com.cinemaapp.backend.service.domain.response;

public class PaymentCreationResponse {

    private String status;
    private String message;

    public PaymentCreationResponse(String status, String message) {
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
