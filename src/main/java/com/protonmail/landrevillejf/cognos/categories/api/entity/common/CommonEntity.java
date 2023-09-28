package com.protonmail.landrevillejf.cognos.categories.api.entity.common;


import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2019-01-01",
        revision = 1,
        comments = "Author CommonEntity"
)
@Getter
@Setter
@MappedSuperclass
public class CommonEntity implements Serializable{

    /** id.
     *
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** uid.
     *
     */
    @Column(name = "uid", nullable = false)
    private String uid;
}
