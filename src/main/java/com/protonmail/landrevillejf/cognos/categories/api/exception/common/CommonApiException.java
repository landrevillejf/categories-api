package com.protonmail.landrevillejf.cognos.categories.api.exception.common;

public class CommonApiException extends RuntimeException {

    public CommonApiException(String message) {
        super(message);
    }

    public CommonApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
