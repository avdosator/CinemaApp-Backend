package com.cinemaapp.backend.service.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreatePaymentIntentRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID projectionInstanceId;

    @NotNull
    @Min(1)
    private int amount;

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
}
