package com.protonmail.landrevillejf.cognos.categories.api.exception;

public enum ApiExceptionEnums {
    OBJECT_EXIST_EXCEPTION("Object already exist","Object already exist exception"),
    OBJECT_NOT_FOUND("Object not found","Object not found exception"),
    LIST_ALREADY_EMPTY("List already empty","List already empty exception"),
    FIELDS_NULL_EXCEPTION("Null Fields","Fields can't be null"),
    EMPTY_LIST("Empty List","List is empty");


    final String name;
    final String description;

    ApiExceptionEnums(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
