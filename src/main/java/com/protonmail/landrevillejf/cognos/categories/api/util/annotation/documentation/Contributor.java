/**
 * This package contains custom annotations used for documentation purposes.
 * These annotations are intended to provide information about authors, maintainers,
 * contributors, revisions, and licensing for various code elements.
 */
package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.Documented;

/**
 * The {@code Contributor} annotation is used to specify information about a contributor to code or documentation.
 * It includes details such as the contributor's name, enterprise, email, and website.
 */
@SuppressWarnings("CheckStyle")
@Documented
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        author =  "Jean-Francois Landreville",
        date = "2022-10-28",
        revision = 1,
        comments = "Contributor Annotation"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
public @interface Contributor {
    String name() default "Contributor's Name";
    String enterprise() default "Contributor's Enterprise";
    String email() default "Contributor's email";
    String website() default "Author's website";
}
