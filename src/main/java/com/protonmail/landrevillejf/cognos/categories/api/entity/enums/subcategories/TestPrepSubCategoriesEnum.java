package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@SuppressWarnings("CheckStyle")
@Getter
public enum TestPrepSubCategoriesEnum {
    GRAD_ENTRY_EXAM("Grad Entry Exam","Grad Entry Exam"),
    INTERNATIONAL_HIGH_SCHOOL("International High School","International High School"),
    COLLEGE_ENTRY_EXAM("College Entry Exam","College Entry Exam"),
    TEST_TAKING_SKILLS("Test Taking Skills","Test Taking Skills"),
    OTHER("Test Preparations Other","Test Preparations Other");

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
    TestPrepSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
