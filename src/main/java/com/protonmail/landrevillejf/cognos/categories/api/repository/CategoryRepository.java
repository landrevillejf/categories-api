/**
 * Repository interface for managing {@link Category} entities.
 */
package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.repository.common.CommonRepository;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Maintainer(name = "Jean-Francois Landreville",
        enterprise = "Lanaforge Inc.",
        email = "landrevillejf@protonmail.com",
        website = "https://www.lanaforge.ca"
)
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author CategoryRepository"
)
@Repository
public interface CategoryRepository extends CommonRepository<Category> {
    /**
     * Finds a category by its name.
     *
     * @param name The name of the category to find.
     * @return The category with the specified name, or null if not found.
     */
    Category findByName(String name);

    /**
     * Finds categories whose name contains the given search term, returning results in a pageable format.
     *
     * @param pageable The paging information.
     * @param search   The search term to filter categories by name.
     * @return A pageable list of categories containing the search term in their names.
     */
    Page<Category> findByNameContaining(Pageable pageable, String search);

    /**
     * Checks if a category with the given name exists.
     *
     * @param name The name to check for existence.
     * @return True if a category with the specified name exists, false otherwise.
     */
    boolean existsByName(String name);
}

