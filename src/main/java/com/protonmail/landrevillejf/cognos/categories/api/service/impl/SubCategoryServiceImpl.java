package com.protonmail.landrevillejf.cognos.categories.api.service.impl;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.cognos.categories.api.repository.SubCategoryRepository;
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
public class SubCategoryServiceImpl implements ICommonService<SubCategory> {

    @Autowired
    private SubCategoryRepository repository;

    //region Find Category
    @Override
    public List<SubCategory> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<SubCategory> categoryPage= repository.findAll(pageable);

        return categoryPage.getContent();
    }

    @Override
    public List<SubCategory> findAllByCriteria(int page, int limit, String search) {
        if(page>0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<SubCategory> featureTogglePage = null;
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
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }
        entity.setName(entity.getName().toLowerCase());
        entity.setUid(UUIDGenerator.generateType1UUID().toString());
        return repository.save(entity);
    }
    //endregion

    //region Update Category
    @Override
    public SubCategory update(SubCategory entity) {
        SubCategory category=findByUid(entity.getUid());
        if(category == null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        category.setName(entity.getName().toLowerCase());
        category.setDescription(entity.getDescription());

        return repository.save(category);
    }

    @Override
    public SubCategory update(SubCategory entity, String uid) {
        SubCategory category=findByUid(uid);
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
    public void delete(SubCategory entity) {
//        We Can Check List Before Deleted but that will take some time :)

        int categoryListSize= repository.findAll().size();
        if(categoryListSize <1) {
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        SubCategory category=findByUid(uid);
        if(category == null){
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(category);
    }

    @Override
    public void deleteAll() {
//        We Can Check List Before Deleted but that will take some time :)

//        Integer categoryListSize=categoryRepository.findAll().size();
//        if(categoryListSize <1) {
//            throw new CommonServiceException("Category list already empty !");
//        }
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
