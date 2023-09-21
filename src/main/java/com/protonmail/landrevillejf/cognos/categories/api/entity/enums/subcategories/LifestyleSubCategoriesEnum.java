package com.protonmail.landrevillejf.cognos.categories.api.entity.enums.subcategories;

import lombok.Getter;

@Getter
public enum LifestyleSubCategoriesEnum {
    Arts_Crafts("Arts & Crafts","Arts & Crafts"),
    Foods_Beverages("Foods &Beverages","Foods & Beverages"),
    Beauty_Makeup("Beauty & Makeup","Beauty & Makeup"),
    Travel("Travel","Travel"),
    Gaming("Gaming","Gaming"),
    Home_Improvement("Home Improvement","Home Improvement"),
    Pet_Care_Training("Pet Care & Training","Pet Care & Training"),
    OTHER("Lifestyle Other","Lifestyle Other");

    private final String displayName;
    private final String description;

    LifestyleSubCategoriesEnum(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
}
