package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryDto;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import org.modelmapper.ModelMapper;

public class DtoToObjectConverter {
    /**
     * ModelMapper
     */
    private static ModelMapper modelMapper = new ModelMapper();

    /**
     *
     * @param modelMapper
     */
    public DtoToObjectConverter(final ModelMapper modelMapper) {
        DtoToObjectConverter.modelMapper = modelMapper;
    }

    /**
     *
     * @param categoryDto
     * @return
     */
    public static Category convertCategoryDtoToCategory(final CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
