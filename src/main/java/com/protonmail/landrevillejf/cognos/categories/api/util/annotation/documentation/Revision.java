package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.*;

@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        author =  "Jean-Francois Landreville",
        date = "2022-10-28",
        revision = 1,
        comments = "RevisionInfo Annotation"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.PACKAGE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Revision {
    String author() default "Jean-Francois Landreville";
    String date();
    int revision() default 1;
    String comments();
}
