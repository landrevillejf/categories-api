package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum PersonalDevelopmentSubCategoriesEnum {
    PERSONAL_TRANSFORMATION("Personal Transformation","Personal Transformation"),
    LEADERSHIP("Leadership","Leadership"),
    PRODUCTIVITY("Productivity","Productivity"),
    PERSONAL_FINANCE("Personal Finance","Personal Finance"),
    CAREER_DEVELOPMENT("Career Development","Career Development"),
    PARENTING_RELATIONSHIPS("Parenting Relationships","Parenting Relationships"),
    HAPPINESS("Happiness","Happiness"),
    RELIGION_SPIRITUALITY("Religion Spirituality","Religion Spirituality"),
    PERSONAL_BRAND_BUILDING("Personal Brand Building","Personal Brand Building"),
    CREATIVITY("Creativity","Creativity"),
    INFLUENCE("Influence","Influence"),
    STRESS_MANAGEMENT("Stress Management","Stress Management"),
    SELF_ESTEEM("Self Esteem","Self Esteem"),
    MEMORY_STUDY_SKILLS("Memory Study Skills","Memory Study Skills"),
    MOTIVATION("Motivation","Motivation"),
    OTHER("Personal Development Other","Personal Development Other");

    private final String displayName;
    private final String description;

    PersonalDevelopmentSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
