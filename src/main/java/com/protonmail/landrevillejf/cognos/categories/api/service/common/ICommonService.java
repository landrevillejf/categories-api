package com.protonmail.landrevillejf.cognos.categories.api.service.common;

import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * An interface defining common CRUD (Create, Read, Update, Delete) operations for entities.
 *
 * @param <E> The type of entity this service works with, extending {@link CommonEntity}.
 */
@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author ICommonService"
)
public interface ICommonService<E extends CommonEntity> {

    /**
     * Retrieves a list of entities with pagination.
     *
     * @param page  The page number (0-based).
     * @param limit The maximum number of entities to retrieve per page.
     * @return A list of entities on the specified page with the given limit.
     */
    List<E> findAll(int page, int limit);

    /**
     * Retrieves a list of entities by applying search criteria with pagination.
     *
     * @param page   The page number (0-based).
     * @param limit  The maximum number of entities to retrieve per page.
     * @param search The search criteria to filter entities (e.g., name).
     * @return A list of entities on the specified page with the given limit that match the search criteria.
     */
    List<E> findAllByCriteria(int page, int limit, @Param("search") String search);

    /**
     * Finds an entity by its unique identifier (ID).
     *
     * @param id The unique identifier of the entity to find.
     * @return The entity with the specified ID, or null if not found.
     */
    E findById(long id);

    /**
     * Finds an entity by its unique identifier (UID).
     *
     * @param uid The unique identifier (UID) of the entity to find.
     * @return The entity with the specified UID, or null if not found.
     */
    E findByUid(String uid);

    /**
     * Saves an entity.
     *
     * @param entity The entity to be saved.
     * @return The saved entity.
     */
    E save(E entity);

    /**
     * Updates an existing entity.
     *
     * @param entity The entity to be updated.
     * @return The updated entity.
     */
    E update(E entity);

    /**
     * Updates an existing entity with a specific UID.
     *
     * @param entity The entity to be updated.
     * @param uid    The unique identifier (UID) of the entity to be updated.
     * @return The updated entity.
     */
    E update(E entity, String uid);

    /**
     * Deletes an entity.
     *
     * @param entity The entity to be deleted.
     */
    void delete(E entity);

    /**
     * Deletes an entity by its unique identifier (UID).
     *
     * @param uid The unique identifier (UID) of the entity to be deleted.
     */
    void deleteByUid(String uid);

    /**
     * Deletes all entities of this type.
     */
    void deleteAll();

    /**
     * Counts the total number of entities of this type.
     *
     * @return The total count of entities.
     */
    long count();
}

