/**
 * This package contains custom annotations used for documentation purposes.
 * These annotations are intended to provide information about authors, maintainers,
 * contributors, revisions, and licensing for various code elements.
 */
package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Revision} annotation is used to specify revision information for code or documentation.
 * It includes details such as the author, date, revision number, and comments regarding the revision.
 */
@SuppressWarnings("CheckStyle")
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
