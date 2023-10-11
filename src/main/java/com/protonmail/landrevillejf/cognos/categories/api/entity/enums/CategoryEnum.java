package com.protonmail.landrevillejf.cognos.categories.api.entity.enums;

/**
 * Enumeration representing various categories for e-learning courses.
 */

import lombok.Getter;


@SuppressWarnings("CheckStyle")
@Getter
public enum CategoryEnum {
    BUSINESS("Business", "Business"),
    DEVELOPMENT("Development", "Development"),
    FINANCE_ACCOUNTING("Finance & Accounting", "Finance & Accounting"),
    IT_SOFTWARE("IT & Software", "IT & Software"),
    OFFICE_PRODUCTIVITY("Office Productivity", "Office Productivity"),
    PERSONAL_DEVELOPMENT("Personal Development", "Personal Development"),
    DESIGN("Design", "Design"),
    MARKETING("Marketing", "Marketing"),
    LIFESTYLE("Lifestyle", "Lifestyle"),
    PHOTOGRAPHY("Photography", "Photography"),
    VIDEO("Video", "Video"),
    HEALTH("Health", "Health"),
    FITNESS("Fitness", "Fitness"),
    MUSIC("Music", "Music"),
    TEACHING("Teaching", "Teaching"),
    ACADEMICS("Academics", "Academics"),
    TEST_PREP("Test Preparation", "Test Preparation"),
    LANGUAGE("Language", "Language");

    /**
     * Display name of the category.
     */
    private final String displayName;

    /**
     * Description of the category.
     */
    private final String description;

    /**
     * Constructor to initialize the display name and description of the category.
     *
     * @param displayName Display name of the category.
     * @param description Description of the category.
     */
    CategoryEnum(final String displayName, final String description) {
        this.displayName = displayName;
        this.description = description;
    }
}

