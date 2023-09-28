package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.Documented;

@Documented
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.",email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        author =  "Jean-Francois Landreville",
        date = "2022-10-28",
        revision = 1,
        comments = "Author Annotation"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
public @interface Author {
    String name() default "Author's Name";
    String enterprise() default "Author's Enterprise";
    String email() default "Author's email";
    String website() default "Author's website";
}
