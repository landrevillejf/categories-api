package com.protonmail.landrevillejf.cognos.categories.api.entity.common;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@SuppressWarnings("CheckStyle")
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
