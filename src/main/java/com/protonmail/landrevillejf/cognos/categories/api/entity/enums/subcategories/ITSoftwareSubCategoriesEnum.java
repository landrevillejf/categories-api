package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum ITSoftwareSubCategoriesEnum {
    IT_CERTIFICATION("IT Certification","IT Certification"),
    NETWORK_SECURITY("Network Security","Network Security"),
    HARDWARE("Hardware","Hardware"),
    OPERATING_SYSTEMS("Operating Systems","Operating Systems"),
    OTHER("IT & Software Other","IT & Software Other");

    private final String displayName;
    private final String description;

    ITSoftwareSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
