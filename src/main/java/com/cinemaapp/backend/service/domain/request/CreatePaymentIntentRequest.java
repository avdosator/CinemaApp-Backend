package com.cinemaapp.backend.service.domain.request;

import com.cinemaapp.backend.service.domain.model.Seat;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class CreatePaymentIntentRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID projectionInstanceId;

    @NotNull
    private List<Seat> selectedSeats;

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

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<Seat> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}
