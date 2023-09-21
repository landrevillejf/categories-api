package com.protonmail.landrevillejf.cognos.categories.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception{

    private static final long serialVersionUID = 7161951225111809442L;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message,cause);
    }
}
