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

    public @NotNull UUID getUserId() {
        return userId;
    }

    public void setUserId(@NotNull UUID userId) {
        this.userId = userId;
    }

    public @NotNull UUID getProjectionInstanceId() {
        return projectionInstanceId;
    }

    public void setProjectionInstanceId(@NotNull UUID projectionInstanceId) {
        this.projectionInstanceId = projectionInstanceId;
    }

    @NotNull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }

    public @NotNull UUID[] getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(@NotNull UUID[] selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}
