package com.protonmail.landrevillejf.cognos.categories.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class EntityDtoMapper {

    /**
     *
     */
    private static final ModelMapper modelMapper = new ModelMapper();

    // Convert Entity to DTO

    /**convertToDto.
     * CategoryDto categoryDto = EntityDtoMapper.convertToDto(category, CategoryDto.class);
     * @param entity
     * @param dtoClass
     * @return
     * @param <D>
     * @param <E>
     */
    public static <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    // Convert DTO to Entity

    /**convertToEntity.
     * Category category = EntityDtoMapper.convertToEntity(categoryDto, Category.class);
     * @param dto
     * @param entityClass
     * @return
     * @param <D>
     * @param <E>
     */
    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    // If you have more complex mappings or specific configuration, you can configure ModelMapper here
    static {
        // Example configuration (customize as needed)
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
