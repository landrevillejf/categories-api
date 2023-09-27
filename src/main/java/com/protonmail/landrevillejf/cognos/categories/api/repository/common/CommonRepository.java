package com.protonmail.landrevillejf.cognos.categories.api.repository.common;

import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@SuppressWarnings("CheckStyle")
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
