package com.protonmail.landrevillejf.cognos.categories.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("CheckStyle")
public class EntityDtoMapper {

    /**
     * ModelMapper instance for EntityDtoMapper class.
     */
    private static final ModelMapper modelMapper = new ModelMapper();

    /**convertToDto.
     * CategoryDto categoryDto = EntityDtoMapper.convertToDto(category, CategoryDto.class);
     * @param entity entity object.
     * @param dtoClass dto object.
     * @return A Dto object.
     * @param <D> Type of the DTO.
     * @param <E> Type of the entity.
     */
    public static <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    /**convertToEntity.
     * Category category = EntityDtoMapper.convertToEntity(categoryDto, Category.class);
     * @param dto dto object.
     * @param entityClass entity object.
     * @return An Entity object.
     * @param <D> Type of the DTO.
     * @param <E> Type of the entity.
     */
    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Convert a list of entities to a list of DTOs.
     * @param entities  List of entity objects.
     * @param dtoClass  Class of the DTO.
     * @param <D>       Type of the DTO.
     * @param <E>       Type of the entity.
     * @return          List of DTO objects.
     */
    public static <D, E> List<D> convertToDtoList(List<E> entities, Class<D> dtoClass) {
        return entities.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    static {
         modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
