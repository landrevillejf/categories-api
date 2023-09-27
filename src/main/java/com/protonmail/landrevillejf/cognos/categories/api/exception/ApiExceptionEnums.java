package com.protonmail.landrevillejf.cognos.categories.api.exception;

import lombok.Getter;

@Getter
@SuppressWarnings("CheckStyle")
public enum ApiExceptionEnums {
    OBJECT_EXIST_EXCEPTION("Object already exist","Object already exist exception"),
    OBJECT_NOT_FOUND("Object not found","Object not found exception"),
    LIST_ALREADY_EMPTY("List already empty","List already empty exception"),
    FIELDS_NULL_EXCEPTION("Null Fields","Fields can't be null"),
    EMPTY_LIST("Empty List","List is empty");

    /**
     *
     */
    final String name;
    /**
     *
     */
    final String description;

    /**
     *
     * @param name
     * @param description
     */
    ApiExceptionEnums(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
}
