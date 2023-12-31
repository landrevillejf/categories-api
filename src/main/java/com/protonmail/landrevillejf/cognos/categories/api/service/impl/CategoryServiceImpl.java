package com.protonmail.landrevillejf.cognos.categories.api.service.impl;


import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.CommonServiceException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.ExecutionTime;
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

@SuppressWarnings("CheckStyle")
@Transactional
@Service
public class CategoryServiceImpl implements ICommonService<Category> {
    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * The repository for Category entities.
     */
    private final CategoryRepository repository;

    /**
     * The repository for SubCategory entities.
     */
    private final SubCategoryRepository subCategoryRepository;

    /**
     * Constructs a new CategoryServiceImpl with the specified repositories.
     *
     * @param repository            The repository for Category entities.
     * @param subCategoryRepository The repository for SubCategory entities.
     */
    public CategoryServiceImpl(final CategoryRepository repository, SubCategoryRepository subCategoryRepository) {
        this.repository = repository;
        this.subCategoryRepository = subCategoryRepository;
    }

    //region Find Category
    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @ExecutionTime
    @Override
    public List<Category> findAll(int page, int limit) {
        Page<Category> categoryPage = null;
        try {
            if (page > 0) {
                page -= 1;
                Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
                categoryPage = repository.findAll(pageable);
            }
        }
        catch (Exception e) {
            logger.error("Error in findAll: " + e.getMessage(), e);
            throw new CommonServiceException("Error while fetching data.", e);
        }
        assert categoryPage != null;
        return categoryPage.getContent();
    }

    /**
     *
     * @param page
     * @param limit
     * @param search
     * @return
     */
    @ExecutionTime
    @Override
    public List<Category> findAllByCriteria(int page, final int limit, final String search) {
        Pageable pageable = null;
        Page<Category> categories = null;
        try {
            if (page > 0) {
                page -= 1;
                pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
            }
            if (search == null) {
                assert pageable != null;
                categories = repository.findAll(pageable);
            } else if (search.isEmpty()) {
                assert pageable != null;
                categories = repository.findAll(pageable);
            } else {
                categories = repository.findByNameContaining(pageable, search);
            }
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while fetching data.", e);
        }
        assert categories != null;
        return categories.getContent();
    }

    /**
     *
     * @param id
     * @return
     */
    @ExecutionTime
    @Override
    public Category findById(long id) {
        Optional<Category> category;
        try {
            category = repository.findById(id);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while fetching data.", e);
        }
        return category.orElse(null);
    }

    /**
     * Find a Category entity by its unique identifier (UID).
     *
     * @param uid The UID of the Category to find.
     * @return The found Category entity, or null if not found.
     */
    @ExecutionTime
    @Override
    public Category findByUid(String uid) {
        Category category;
        try {
            category = repository.findByUid(uid);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while fetching data.", e);
        }
        return category;
    }
    //endregion

    //region Save Category
    /**
     * Save a new Category entity.
     *
     * @param entity The Category entity to save.
     * @return The saved Category entity.
     */
    @ExecutionTime
    @Override
    public Category save(Category entity) {
        String categoryName = entity.getName();

        Category getCategory = repository.findByName(categoryName);
        if (getCategory != null) {
            logger.error(ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
            throw new CommonServiceException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }

        try {
            List<SubCategory> subCategories = entity.getSubCategories();
            if (subCategories != null) {
                for (SubCategory subCategory : subCategories) {
                    if (subCategory.getUid() == null) {
                        logger.error("ERROR");
                        throw new CommonServiceException("Subcategory UID is required");
                    }
                    SubCategory existingSubCategory = subCategoryRepository.findByUid(subCategory.getUid());
                    if (existingSubCategory == null) {
                        logger.error("Subcategory not found");
                        throw new CommonServiceException("Subcategory not found with UID: " + subCategory.getUid());
                    }
                    subCategory.setCategory(existingSubCategory.getCategory());
                }
            }

            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUid(UUIDGenerator.generateType1UUID().toString());
            entity.setCreatedAt(LocalDateTime.now());
        } catch (Exception e) {
            logger.error("Failed to save values: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while saving data.", e);
        }

        return repository.save(entity);
    }
    //endregion

    //region Update Category
    /**
     * Update an existing Category entity.
     *
     * @param entity The updated Category entity.
     * @return The updated Category entity.
     */
    @ExecutionTime
    @Override
    public Category update(Category entity) {
        Category category = findByUid(entity.getUid());
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonServiceException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            category.setName(entity.getName());
            category.setDescription(entity.getDescription());
            category.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to update values: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while updating data.", e);
        }

        return repository.save(category);
    }

    /**
     * Update an existing Category entity by its UID.
     *
     * @param entity The updated Category entity.
     * @param uid    The UID of the Category to update.
     * @return The updated Category entity.
     */
    @ExecutionTime
    @Override
    public Category update(Category entity, String uid) {
        Category category = findByUid(uid);
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonServiceException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            logger.error("Failed to update values: {}", e.getMessage(), e);
            throw new CommonServiceException("Error while updating data.", e);
        }

        return repository.save(category);
    }
    //endregion

    //region Delete Category

    /**
     * Delete a Category entity.
     *
     * @param entity The Category entity to delete.
     */
    @ExecutionTime
    @Override
    public void delete(Category entity) {
        Category existingCategory = repository.findByUid(entity.getUid());
        if (existingCategory == null) {
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonServiceException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }

        List<SubCategory> subCategories = existingCategory.getSubCategories();

        if (subCategories != null && !subCategories.isEmpty()) {
            subCategoryRepository.deleteAll(subCategories);
        }

        repository.delete(existingCategory);
    }


    /**
     * Delete a Category entity by its UID.
     *
     * @param uid The UID of the Category to delete.
     */
    @ExecutionTime
    @Override
    public void deleteByUid(String uid) {
        Category category = findByUid(uid);
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonServiceException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }

    /**
     * Delete all Category entities.
     */
    @ExecutionTime
    @Override
    public void deleteAll() {
        int size = repository.findAll().size();
        if (size < 1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonServiceException(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.deleteAll();
    }
    //endregion

    //region Count Category

    /**
     * Get the total count of Category entities.
     *
     * @return The total count of Category entities.
     */
    @ExecutionTime
    @Override
    public long count() {
        return repository.count();
    }
    //endregion
}
