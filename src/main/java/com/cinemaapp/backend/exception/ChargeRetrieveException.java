package com.cinemaapp.backend.exception;

public class ChargeRetrieveException extends RuntimeException {

    public ChargeRetrieveException(String message) {
        super(message);
    }

    public ChargeRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }
}
