package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum DevelopmentSubCategoriesEnum {
    WEB_DEVELOPMENT("Web Development","Web Development"),
    MOBILE_APPS("Mobile Apps","Mobile Apps"),
    PROGRAMMING("Programming","Programming"),
    GAME_DEVELOPMENT("Game Development","Game Development"),
    DATABASES("Databases","Databases"),
    SOFTWARE_TESTING("Software Testing","Software Testing"),
    SOFTWARE_ENGINEERING("Software engineering","Software engineering"),
    DEVELOPMENT_TOOLS("Development Tools","Development Tools"),
    ECOMMERCE("E-Commerce","E-Commerce");

    /**
     *
     */
    private final String displayName;
    /**
     *
     */
    private final String description;

    DevelopmentSubCategoriesEnum(final String displayName, final String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
