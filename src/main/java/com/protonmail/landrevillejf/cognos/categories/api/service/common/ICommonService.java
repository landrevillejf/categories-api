package com.protonmail.landrevillejf.cognos.categories.api.service.common;

import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.springframework.data.repository.query.Param;

import java.util.List;
@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author ICommonService"
)
public interface ICommonService<E extends CommonEntity> {
    List<E> findAll(int page,int limit);
    List<E> findAllByCriteria(int page, int limit, @Param("search") String search);
    E findById(long id);
    E findByUid(String uid);
    E save(E entity);
    E update(E entity);
    E update(E entity,String uid);
    void delete(E entity);
    void deleteByUid(String uid);
    void deleteAll();
    long count();
}
