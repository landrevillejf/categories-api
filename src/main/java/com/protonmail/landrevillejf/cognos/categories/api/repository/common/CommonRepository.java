package com.protonmail.landrevillejf.cognos.categories.api.repository.common;

import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author CommonRepository"
)
/**
//@NoRepositoryBean To exclude this Repository from being instantiated
**/
@NoRepositoryBean
public interface CommonRepository<E extends CommonEntity> extends JpaRepository<E, Long> {
    /**
     *
     * @param uid
     * @return
     */
    E findByUid(String uid);
}
