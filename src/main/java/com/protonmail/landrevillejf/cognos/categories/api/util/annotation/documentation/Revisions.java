package com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation;

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
        comments = "Revisions"
)
@License(name = "Apache", version = "2.0", site = "https://www.apache.org/licenses/LICENSE-2.0.html")
public @interface Revisions {
    Revision[] value();
}
