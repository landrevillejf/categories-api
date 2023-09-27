package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
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
public class SubCategoryServiceImpl implements ICommonService<SubCategory> {
    /**
     *
     */
    Logger logger = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    /**
     *
     */
    private final SubCategoryRepository repository;

    /**
     *
     * @param repository
     */
    public SubCategoryServiceImpl(SubCategoryRepository repository) {
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
    public List<SubCategory> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "id"));
        Page<SubCategory> categoryPage= repository.findAll(pageable);

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
    public List<SubCategory> findAllByCriteria(int page, final int limit, final String search) {
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

    @Override
    public SubCategory findById(long id) {
        Optional <SubCategory> category = repository.findById(id);
        return category.orElse(null);
    }

    @Override
    public SubCategory findByUid(String uid) {
        return repository.findByUid(uid);
    }
    //endregion

    //region Save Category
    @Override
    public SubCategory save(SubCategory entity) {
        String categoryName=(entity.getName().trim()).toLowerCase();

        SubCategory getCategory= repository.findByName(categoryName);
        if(getCategory != null){
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
    @Override
    public SubCategory update(SubCategory entity) {
        SubCategory category=findByUid(entity.getUid());
        if(category == null){
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

    @Override
    public SubCategory update(SubCategory entity, String uid) {
        SubCategory category=findByUid(uid);
        if(category == null){
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
    @Override
    public void delete(SubCategory entity) {
        int categoryListSize= repository.findAll().size();
        if(categoryListSize <1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        SubCategory category=findByUid(uid);
        if(category == null){
            logger.error(ApiExceptionEnums.OBJECT_NOT_FOUND.name());
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }

    @Override
    public void deleteAll() {
        int categoryListSize=repository.findAll().size();
        if(categoryListSize <1) {
            logger.error(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
            throw new CommonApiException(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.deleteAll();
    }
    //endregion

    //region Count Category
    @Override
    public long count() {
        return repository.count();
    }
    //endregion
}
