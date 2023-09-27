package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum BusinessSubCategoriesEnum {
    ENTREPRENEURSHIP("Entrepreneurship","Entrepreneurship"),
    COMMUNICATION("Communication","Communication"),
    MANAGEMENT("Management","Management"),
    SALES("Sales","Sale"),
    STRATEGY("Strategy","Strategy"),
    OPERATIONS("Operations","Operations"),
    PROJECT_MANAGEMENT("Project Management","Project Management"),
    BUSINESS_LAW("Business Law","Business Law"),
    DATA_ANALYTICS("Data Analytics","Data Analytics"),
    HOME_BUSINESS("Home Business","Home Business"),
    HUMAN_RESOURCES("Human Resources","Human Resources"),
    INDUSTRY("Industry","Industry"),
    MEDIA("Media","Media"),
    REAL_ESTATES("Real Estates","Real Estates"),
    OTHER("Business Other","Business Other");

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
    BusinessSubCategoriesEnum(final String displayName, final String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
