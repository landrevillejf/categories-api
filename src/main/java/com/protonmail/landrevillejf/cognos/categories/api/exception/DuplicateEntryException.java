package com.protonmail.landrevillejf.cognos.categories.api.exception;

@SuppressWarnings("CheckStyle")
public class DuplicateEntryException extends RuntimeException {
    /**
     *
     * @param message
     */
    public DuplicateEntryException(final String message) {
        super(message);
    }
}
