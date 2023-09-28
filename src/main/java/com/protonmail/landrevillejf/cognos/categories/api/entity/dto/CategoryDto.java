package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private Set<SubCategoryDto> subcategories;
}
