package com.protonmail.landrevillejf.cognos.categories.api.exception.common;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;

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
     *
     * @param message
     */
    public CommonApiException(final String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public CommonApiException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
