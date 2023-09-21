package com.protonmail.landrevillejf.cognos.categories.api.entity.enums;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    BUSINESS("Business","Business"),
    DEVELOPMENT("Development","Development"),
    FINANCE_ACCOUNTING("Finance & Accounting","Finance & Accounting"),
    IT_SOFTWARE("IT & Software","IT & Software"),
    OFFICE_PRODUCTIVITY("Office Productivity","Office Productivity"),
    PERSONAL_DEVELOPMENT("Personal Development","Personal Development"),
    DESIGN("Design","Design"),
    MARKETING("Marketing","Marketing"),
    LIFESTYLE("Lifestyle","Lifestyle"),
    PHOTOGRAPHY("Photography","Photography"),
    VIDEO("Video","Video"),
    HEALTH("Health","Health"),
    FITNESS("Fitness","Fitness"),
    MUSIC("Music","Music"),
    TEACHING("Teaching","Teaching"),
    ACADEMICS("Academics","Academics"),
    TEST_PREP("Test Preparation","Test Preparation"),
    LANGUAGE("Language","Language");

    private final String displayName;
    private final String description;

    CategoryEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

}
