package com.protonmail.landrevillejf.cognos.categories.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@SuppressWarnings("CheckStyle")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 7161951225111809442L;

    /**
     *
     * @param message
     */
    public EntityNotFoundException(final String message){
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public EntityNotFoundException(final String message, final Throwable cause) {
        super(message,cause);
    }
}
