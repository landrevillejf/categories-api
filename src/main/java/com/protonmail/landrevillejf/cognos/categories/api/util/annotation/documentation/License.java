/**
 * This package contains custom annotations used for documentation purposes.
 * These annotations are intended to provide information about authors, maintainers,
 * contributors, revisions, and licensing for various code elements.
 */
package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.Documented;

/**
 * The {@code License} annotation is used to specify licensing information for code or documentation.
 * It includes details such as the license's name, version, website, and an optional license identifier.
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
        comments = "Author Annotation"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
public @interface License {
    String name() default "License's Name";
    String version() default "License's Version";
    String site() default "License's Website";
    int license() default 1;
}
