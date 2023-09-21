package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum AcademicsSubCategoriesEnum {
    Social_Science("Social Science","Social Science"),
    Math_Science("Math Science","Math Science"),
    Humanities("Humanities","Humanities");

    private final String displayName;
    private final String description;

    AcademicsSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
