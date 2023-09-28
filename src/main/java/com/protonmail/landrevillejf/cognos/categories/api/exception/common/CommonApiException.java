package com.protonmail.landrevillejf.cognos.categories.api.exception.common;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;

/**
 * A custom exception class for common API-related exceptions.
 *
 */
@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author CommonApiException"
)
public class CommonApiException extends RuntimeException {
    /**
     * Constructs a new CommonApiException with the specified error message.
     *
     * @param message The error message.
     */
    public CommonApiException(final String message) {
        super(message);
    }

    /**
     * Constructs a new CommonApiException with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause   The cause of this exception.
     */
    public CommonApiException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
