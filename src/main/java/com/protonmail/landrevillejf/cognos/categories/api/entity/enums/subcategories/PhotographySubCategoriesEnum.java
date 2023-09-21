package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum PhotographySubCategoriesEnum {
    DIGITAL_PHOTOGRAPHY("Digital Photography","Digital Photography"),
    PHOTOGRAPHY_FUNDAMENTALS("Photography Fundamentals","Photography Fundamentals"),
    PORTRAITS("Portraits","Portraits"),
    Landscape("Landscape","Landscape"),
    Black_White("Black & White","Black & White"),
    PHOTOGRAPHY_TOOLS("Photography Tools","Photography Tools"),
    Mobile_PHOTOGRAPHY("Mobile Photography","Mobile Photography"),
    Travel_PHOTOGRAPHY("Travel Photography","Travel Photography"),
    Commercial_PHOTOGRAPHY("Commercial Photography","Commercial Photography"),
    Wedding_PHOTOGRAPHY("Wedding Photography","Wedding Photography"),
    Wildlife_PHOTOGRAPHY("Wildlife Photography","Wildlife Photography"),
    OTHER("Photography Other","Photography Other");

    private final String displayName;
    private final String description;

    PhotographySubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
