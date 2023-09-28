package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.",email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2023-09-26",
        revision = 1,
        comments = "Author UUIDGenerator"
)
public class EntityDtoMapper {

    /**
     *
     */
    private static final ModelMapper modelMapper = new ModelMapper();

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
