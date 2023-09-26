package com.protonmail.landrevillejf.cognos.categories.api.service.impl;


import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CategoryServiceImpl implements ICommonService<Category> {
    /**
     *
     */
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     *
     */
    private final CategoryRepository repository;

    /**
     *
     * @param repository
     */
    public CategoryServiceImpl(final CategoryRepository repository) {
        this.repository = repository;
    }

    //region Find Category
    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Category> findAll(int page, int limit) {
        Page<Category> categoryPage = null;
        if (page > 0) {
            page -= 1;
            Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
            categoryPage = repository.findAll(pageable);
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
        }
        assert categories != null;
        return categories.getContent();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Category findById(long id) {
        Optional <Category> category = repository.findById(id);
        return category.orElse(null);
    }

    /**
     *
     * @param uid
     * @return
     */
    @Override
    public Category findByUid(String uid) {
        return repository.findByUid(uid);
    }
    //endregion

    //region Save Category

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public Category save(Category entity) {
        String categoryName=(entity.getName());

        Category getCategory= repository.findByName(categoryName);
        if (getCategory != null){
            logger.error(ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }

        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
            entity.setUid(UUIDGenerator.generateType1UUID().toString());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to save values: {}", e.getMessage(), e);
        }

        return repository.save(entity);
    }
    //endregion

    //region Update Category

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public Category update(Category entity) {
        Category category = findByUid(entity.getUid());
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to update values: {}", e.getMessage(), e);
        }

        return repository.save(category);
    }

    /**
     *
     * @param entity
     * @param uid
     * @return
     */
    @Override
    public Category update(Category entity, String uid) {
        Category category = findByUid(uid);
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        try {
            entity.setName(entity.getName());
            entity.setDescription(entity.getDescription());
        } catch (Exception e) {
            // Log the exception
            logger.error("Failed to update values: {}", e.getMessage(), e);
        }

        return repository.save(category);
    }
    //endregion

    //region Delete Category

    /**
     *
     * @param entity
     */
    @Override
    public void delete(Category entity) {
        int categoryListSize= repository.findAll().size();
        if (categoryListSize < 1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }

    /**
     *
     * @param uid
     */
    @Override
    public void deleteByUid(String uid) {
        Category category = findByUid(uid);
        if (category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        int size = repository.findAll().size();
        if (size < 1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.deleteAll();
    }
    //endregion

    //region Count Category

    /**
     *
     * @return
     */
    @Override
    public long count() {
        return repository.count();
    }
    //endregion
}
