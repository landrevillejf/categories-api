package com.protonmail.landrevillejf.cognos.categories.api.exception.common;

/**
 * A custom exception class for common API-related exceptions.
 *
 */
@SuppressWarnings("CheckStyle")
public class CommonApiException extends RuntimeException {
    /**
     * Constructs a new CommonApiException with the specified error message.
     *
     * @param message The error message.
     */
    public CommonApiException(final String message) {
        super(message);
    }

    /**
     * Constructs a new CommonApiException with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of this exception.
     */
    public CommonApiException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
