package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CategoryDto {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;
    private Set<SubCategoryDto> subcategories;
}
