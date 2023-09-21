package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.CategoryDto;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import org.modelmapper.ModelMapper;

public class DtoToObjectConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public DtoToObjectConverter(ModelMapper modelMapper) {
        DtoToObjectConverter.modelMapper = modelMapper;
    }

    public static Category convertCategoryDtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
