package com.cinemaapp.backend.service.domain.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class VerifyResetCodeRequest {

    @Email
    private String email;

    @Size(min = 4, max = 4)
    private String resetCode;

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @Size(min = 4, max = 4) String getResetCode() {
        return resetCode;
    }

    public void setResetCode(@Size(min = 4, max = 4) String resetCode) {
        this.resetCode = resetCode;
    }
}
