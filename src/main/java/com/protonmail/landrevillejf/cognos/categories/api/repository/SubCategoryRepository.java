package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CommonRepository<SubCategory> {
    SubCategory findByName(String name);

    Page<SubCategory> findByNameContaining(Pageable pageable, String search);

    boolean existsByName(String s);
}
