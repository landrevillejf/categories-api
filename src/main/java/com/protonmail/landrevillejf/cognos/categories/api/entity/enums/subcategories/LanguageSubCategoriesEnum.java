package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum LanguageSubCategoriesEnum {
    ENGLISH("English","English"),
    SPANISH("Spanish","Spanish"),
    FRENCH("French","French"),
    GERMAN("German","German"),
    JAPANESE("Japanese","Japanese"),
    PORTUGUESE("Portuguese","Portuguese"),
    CHINESE("Chinese","Chinese"),
    RUSSIAN("Russian","Russian"),
    LATIN("Latin","Latin"),
    ARABIC("Arabic","Arabic"),
    HEBREW("Hebrew","Hebrew"),
    ITALIAN("Italian","Italian"),
    OTHER("Other","Other");

    private final String displayName;
    private final String description;

    LanguageSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
