package com.cinemaapp.backend.exception;

public class PdfGeneratingException extends RuntimeException {

    public PdfGeneratingException(String message) {
        super(message);
    }

    public PdfGeneratingException(String message, Throwable cause) {
        super(message, cause);
    }
}
