package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
import com.protonmail.landrevillejf.cognos.categories.api.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CommonRepository<SubCategory> {
    /**
     *
     * @param name
     * @return
     */
    SubCategory findByName(String name);

    /**
     *
     * @param pageable
     * @param search
     * @return
     */
    Page<SubCategory> findByNameContaining(Pageable pageable, String search);

    /**
     *
     * @param s
     * @return
     */
    boolean existsByName(String s);
}
