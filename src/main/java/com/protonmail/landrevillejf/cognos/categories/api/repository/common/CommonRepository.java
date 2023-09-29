package com.protonmail.landrevillejf.cognos.categories.api.repository.common;

import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A common repository interface for entities extending {@link CommonEntity}.
 *
 * @param <E> The type of entity this repository works with, extending {@link CommonEntity}.
 *
 * @see JpaRepository
 */
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
        comments = "Author CommonRepository"
)
@NoRepositoryBean
public interface CommonRepository<E extends CommonEntity> extends JpaRepository<E, Long> {
    /**
     * Finds an entity by its unique identifier (UID).
     *
     * @param uid The unique identifier (UID) of the entity to find.
     * @return The entity with the specified UID, or null if not found.
     */
    E findByUid(String uid);
}
