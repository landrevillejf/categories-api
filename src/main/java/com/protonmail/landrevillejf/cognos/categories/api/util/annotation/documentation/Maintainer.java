/**
 * This package contains custom annotations used for documentation purposes.
 * These annotations are intended to provide information about authors, maintainers,
 * contributors, revisions, and licensing for various code elements.
 */
package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.Documented;

/**
 * The {@code Maintainer} annotation is used to specify information about a maintainer or maintainer team for code or documentation.
 * It includes details such as the maintainer's name, enterprise, email, and website.
 */
@SuppressWarnings("CheckStyle")
@Documented
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
        author =  "Jean-Francois Landreville",
        date = "2022-10-28",
        revision = 1,
        comments = "Author Annotation"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
public @interface Maintainer {
    String name() default "Maintainer's Name";
    String enterprise() default "Maintainer's Enterprise";
    String email() default "Maintainer's email";
    String website() default "Author's website";
}
