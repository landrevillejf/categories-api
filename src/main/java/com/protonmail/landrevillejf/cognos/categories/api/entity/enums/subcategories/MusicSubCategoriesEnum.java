package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum MusicSubCategoriesEnum {
    INSTRUMENTS("Instruments","Instruments"),
    PRODUCTION("Production","Production"),
    MUSIC_FUNDAMENTALS("Music Fundamentals","Music Fundamentals"),
    VOCALS("Vocals","Vocals"),
    MUSIC_TECHNIQUES("Music Techniques","Music Techniques"),
    MUSIC_SOFTWARE("Music Software","Music Software"),
    OTHER("Music Other","Music Other");

    private final String displayName;
    private final String description;

    MusicSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
