package com.protonmail.landrevillejf.cognos.categories.api.exception.common;

public class CommonApiException extends RuntimeException {
    /**
     *
     * @param message
     */
    public CommonApiException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public CommonApiException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
