package com.protonmail.landrevillejf.cognos.categories.api.config;


import com.protonmail.landrevillejf.cognos.categories.api.exception.DuplicateEntryException;
import com.protonmail.landrevillejf.cognos.categories.api.exception.EntityNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SuppressWarnings("CheckStyle")
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConversion(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
