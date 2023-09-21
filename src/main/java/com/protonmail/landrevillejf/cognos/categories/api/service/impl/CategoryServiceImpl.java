package com.protonmail.landrevillejf.cognos.categories.api.service.impl;



import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.repository.CategoryRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.UUIDGenerator;
import com.protonmail.landrevillejf.cognos.categories.api.exception.common.CommonApiException;
import com.protonmail.landrevillejf.cognos.categories.api.service.common.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CategoryRepository repository;

    //region Find Category
    @Override
    public List<Category> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Category> categoryPage= repository.findAll(pageable);

        return categoryPage.getContent();
    }

    @Override
    public List<Category> findAllByCriteria(int page, int limit, String search) {
        if(page>0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<Category> featureTogglePage = null;
        if(search ==null){
            featureTogglePage= repository.findAll(pageable);
        }
        else if(search.isEmpty()){
            featureTogglePage= repository.findAll(pageable);
        }else{
            featureTogglePage= repository.findByNameContaining(pageable,search);
        }
        assert featureTogglePage != null;
        return featureTogglePage.getContent();
    }

    @Override
    public Category findById(long id) {
        Optional <Category> category = repository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category findByUid(String uid) {
        return repository.findByUid(uid);
    }
    //endregion

    //region Save Category
    @Override
    public Category save(Category entity) {
        String categoryName=(entity.getName().trim()).toLowerCase();

        Category getCategory= repository.findByName(categoryName);
        if(getCategory != null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }
        entity.setName(entity.getName().toLowerCase());
        entity.setUid(UUIDGenerator.generateType1UUID().toString());
        return repository.save(entity);
    }
    //endregion

    //region Update Category
    @Override
    public Category update(Category entity) {
        Category category=findByUid(entity.getUid());
        if(category == null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        category.setName(entity.getName().toLowerCase());
        category.setDescription(entity.getDescription());

        return repository.save(category);
    }

    @Override
    public Category update(Category entity, String uid) {
        Category category=findByUid(uid);
        if(category == null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        category.setName(entity.getName().toLowerCase());
        category.setDescription(entity.getDescription());

        return repository.save(category);
    }
    //endregion

    //region Delete Category
    @Override
    public void delete(Category entity) {
        int categoryListSize= repository.findAll().size();
        if(categoryListSize <1) {
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        Category category=findByUid(uid);
        if(category == null){
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }

    @Override
    public void deleteAll() {
        int size=repository.findAll().size();
        if(size <1) {
            throw new CommonApiException("Category list already empty !");
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
