package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum TeacherTrainingSubCategories {
    INSTRUCTIONAL_DESIGN("Instructional Design","Instructional Design"),
    EDUCATIONAL_DEVELOPMENT("Educational Development","Educational Development"),
    TEACHING_TOOLS("Teaching Tools","Teaching Tools"),
    OTHER("Teaching & Training Other","Teaching & Training Other");

    private final String displayName;
    private final String description;

    TeacherTrainingSubCategories(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
