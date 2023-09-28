package com.protonmail.landrevillejf.cognos.categories.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("CheckStyle")
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private String resourceName;
    /**
     *
     */
    private String fieldName;
    /**
     *
     */
    private Long fieldValue;
    /**
     *
     */
    private String fieldValueAsString;

    /**
     *
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {

        super(String.format("%s with %s : '%s' not found", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    /**
     *
     * @param resourceName
     * @param fieldName
     * @param fieldValueAsString
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueAsString) {

        super(String.format("%s with %s : '%s' not found", resourceName, fieldName, fieldValueAsString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueAsString = fieldValueAsString;
    }

}
