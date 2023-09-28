package com.protonmail.landrevillejf.cognos.categories.api.exception;

@SuppressWarnings("CheckStyle")
public class ReportGenerationException extends RuntimeException {

    /**
     *
     * @param message
     */
    public ReportGenerationException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public ReportGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
