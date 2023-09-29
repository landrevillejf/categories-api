package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for managing SubCategory entities.
 */
@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author SubCategoryServiceImpl"
)
@Transactional
@Service
public class SubCategoryServiceImpl implements ICommonService<SubCategory> {
    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    /**
     * The repository for SubCategory entities.
     */
    private final SubCategoryRepository repository;

    /**
     * Constructs a new SubCategoryServiceImpl with the specified repository.
     *
     * @param repository The repository for SubCategory entities.
     */
    public SubCategoryServiceImpl(SubCategoryRepository repository) {
        this.repository = repository;
    }

    //region Find Category
    /**
     * Find all SubCategory entities with pagination.
     *
     * @param page  The page number.
     * @param limit The number of SubCategory entities per page.
     * @return A list of SubCategory entities for the specified page and limit.
     */
    @Override
    public List<SubCategory> findAll(int page, int limit) {
        Page<SubCategory> categoryPage = null;
        if (page > 0) {
            page -= 1;
            Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
            categoryPage = repository.findAll(pageable);
        }
        assert categoryPage != null;
        return categoryPage.getContent();
    }

    /**
     * Find all SubCategory entities by criteria with pagination.
     *
     * @param page   The page number.
     * @param limit  The number of SubCategory entities per page.
     * @param search The search criteria for filtering SubCategory entities.
     * @return A list of SubCategory entities based on the criteria.
     */
    @Override
    public List<SubCategory> findAllByCriteria(int page, int limit, String search) {
        Pageable pageable = null;
        Page<SubCategory> subCategories = null;
        try {
            if (page > 0) {
                page -= 1;
                pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
            }
            if (search == null) {
                assert pageable != null;
                subCategories = repository.findAll(pageable);
            } else if (search.isEmpty()) {
                assert pageable != null;
                subCategories = repository.findAll(pageable);
            } else {
                subCategories = repository.findByNameContaining(pageable, search);
            }
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
        assert subCategories != null;
        return subCategories.getContent();
    }

    /**
     * Find a SubCategory entity by its ID.
     *
     * @param id The ID of the SubCategory to find.
     * @return The found SubCategory entity, or null if not found.
     */
    @Override
    public SubCategory findById(long id) {
        Optional <SubCategory> category = repository.findById(id);
        return category.orElse(null);
    }
    /**
     * Find a SubCategory entity by its unique identifier (UID).
     *
     * @param uid The UID of the SubCategory to find.
     * @return The found SubCategory entity, or null if not found.
     */
    @Override
    public SubCategory findByUid(String uid) {
        return repository.findByUid(uid);
    }
    //endregion

    //region Save Category
    /**
     * Save a new SubCategory entity.
     *
     * @param entity The SubCategory entity to save.
     * @return The saved SubCategory entity.
     */
    @Override
    public SubCategory save(SubCategory entity) {
        String categoryName = (entity.getName().trim()).toLowerCase();

        SubCategory getCategory= repository.findByName(categoryName);
        if (getCategory != null){
            logger.error(ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUid(UUIDGenerator.generateType1UUID().toString());
            entity.setCreatedAt(LocalDateTime.now());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to save values: {}", e.getMessage(), e);
        }
        return repository.save(entity);
    }
    //endregion

    //region Update Category
    /**
     * Update an existing SubCategory entity.
     *
     * @param entity The updated SubCategory entity.
     * @return The updated SubCategory entity.
     */
    @Override
    public SubCategory update(SubCategory entity) {
        SubCategory category = findByUid(entity.getUid());
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to update values: {}", e.getMessage(), e);
        }

        return repository.save(category);
    }
    /**
     * Update an existing SubCategory entity by its UID.
     *
     * @param entity The updated SubCategory entity.
     * @param uid    The UID of the SubCategory to update.
     * @return The updated SubCategory entity.
     */
    @Override
    public SubCategory update(SubCategory entity, String uid) {
        SubCategory category = findByUid(uid);
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to update values: {}", e.getMessage(), e);
        }

        return repository.save(category);
    }
    //endregion

    //region Delete Category
    /**
     * Delete a SubCategory entity.
     *
     * @param entity The SubCategory entity to delete.
     */
    @Override
    public void delete(SubCategory entity) {
        int categoryListSize = repository.findAll().size();
        if (categoryListSize < 1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }
    /**
     * Delete a SubCategory entity by its UID.
     *
     * @param uid The UID of the SubCategory to delete.
     */
    @Override
    public void deleteByUid(String uid) {
        SubCategory category=findByUid(uid);
        if(category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }
    /**
     * Delete all SubCategory entities.
     */
    @Override
    public void deleteAll() {
        int categoryListSize = repository.findAll().size();
        if(categoryListSize < 1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.deleteAll();
    }
    //endregion

    //region Count Category
    /**
     * Get the total count of SubCategory entities.
     *
     * @return The total count of SubCategory entities.
     */
    @Override
    public long count() {
        return repository.count();
    }
    //endregion
}
