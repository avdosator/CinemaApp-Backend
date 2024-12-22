package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreatePaymentRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID projectionInstanceId;

    @NotNull
    @Min(1)
    private int amount;

    @NotEmpty
    private UUID[] selectedSeats;

    @NotEmpty
    private String paymentIntentId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProjectionInstanceId() {
        return projectionInstanceId;
    }

    public void setProjectionInstanceId(UUID projectionInstanceId) {
        this.projectionInstanceId = projectionInstanceId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UUID[] getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(UUID[] selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }
}
