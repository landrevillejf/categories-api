package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CommonRepository<Category> {
    /**
     *
     * @param name
     * @return
     */
    Category findByName(String name);

    /**
     *
     * @param pageable
     * @param search
     * @return
     */
    Page<Category> findByNameContaining(Pageable pageable, String search);

    /**
     *
     * @param s
     * @return
     */
    boolean existsByName(String s);
}
