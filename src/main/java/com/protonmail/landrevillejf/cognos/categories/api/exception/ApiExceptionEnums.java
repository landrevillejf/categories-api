package com.protonmail.landrevillejf.cognos.categories.api.exception;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author ApiExceptionEnums"
)
@Getter
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
