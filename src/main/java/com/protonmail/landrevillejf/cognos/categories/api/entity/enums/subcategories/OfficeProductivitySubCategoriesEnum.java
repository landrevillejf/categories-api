package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum OfficeProductivitySubCategoriesEnum {
    MICROSOFT("Microsoft","Microsoft"),
    APPLE("Apple","Apple"),
    GOOGLE("Google","Google"),
    SAP("SAP","SAP"),
    INTUIT("Intuit","Intuit"),
    SALESFORCE("SalesForce","SalesForce"),
    ORACLE("Oracle","Oracle"),
    OTHER("Office & Productivity Other","Office & Productivity Other");

    /**
     *
     */
    private final String displayName;
    /**
     *
     */
    private final String description;

    /**
     *
     * @param displayName
     * @param description
     */
    OfficeProductivitySubCategoriesEnum(final String displayName, final String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
