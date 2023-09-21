package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum HealthFitnessSubCategoriesEnum {
    FITNESS("Fitness","Fitness"),
    GENERAL_HEALTH("General Health","General Health"),
    SPORTS("Sports","Sports"),
    NUTRITION("Nutrition","Nutrition"),
    YOGA("Yoga","Yoga"),
    MENTAL_HEALTH("Mental Health","Mental Health"),
    DIETING("Dieting","Dieting"),
    SELF_DEFENSE("Self Defense","Self Defense"),
    SAFETY_FIRST_AID("Safety First Aid","Safety First Aid"),
    DANCE("Dance","Dance"),
    MEDITATION("Meditation","Meditation"),
    OTHER("Health Other","Health Other");

    private final String displayName;
    private final String description;

    HealthFitnessSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
