package com.protonmail.landrevillejf.cognos.categories.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("CheckStyle")
@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends RuntimeException {
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
    private String fieldValue;

    /**
     *
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue) {

        super(String.format("Resource %s with %s : '%s' already exist", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
