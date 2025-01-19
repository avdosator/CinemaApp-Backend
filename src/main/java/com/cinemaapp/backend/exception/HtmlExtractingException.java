package com.cinemaapp.backend.exception;

public class HtmlExtractingException extends RuntimeException {

    public HtmlExtractingException(String message) {
        super(message);
    }

    public HtmlExtractingException(String message, Throwable cause) {
        super(message, cause);
    }
}
