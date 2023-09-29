/**
 * Repository interface for managing {@link SubCategory} entities.
 */
package com.protonmail.landrevillejf.cognos.categories.api.repository;

import com.protonmail.landrevillejf.cognos.categories.api.entity.model.Category;
import com.protonmail.landrevillejf.cognos.categories.api.entity.model.SubCategory;
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
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author CategoryRepository"
)
@Repository
public interface SubCategoryRepository extends CommonRepository<SubCategory> {
    /**
     * Finds a subcategory by its name.
     *
     * @param name The name of the subcategory to find.
     * @return The subcategory with the specified name, or null if not found.
     */
    SubCategory findByName(String name);

    /**
     * Finds subcategories whose name contains the given search term, returning results in a pageable format.
     *
     * @param pageable The paging information.
     * @param search   The search term to filter subcategories by name.
     * @return A pageable list of subcategories containing the search term in their names.
     */
    Page<SubCategory> findByNameContaining(Pageable pageable, String search);

    /**
     * Checks if a subcategory with the given name exists.
     *
     * @param name The name to check for existence.
     * @return True if a subcategory with the specified name exists, false otherwise.
     */
    boolean existsByName(String name);

    /**
     * Counts the number of subcategories associated with a specific category.
     *
     * @param category The category for which to count subcategories.
     * @return The count of subcategories associated with the given category.
     */
    int countByCategory(Category category);
}
