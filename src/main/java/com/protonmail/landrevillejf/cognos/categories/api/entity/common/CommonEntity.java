package com.protonmail.landrevillejf.cognos.categories.api.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * This is a base class for common entities in the application.
 * It provides common fields like 'id' and 'uid' for entity classes.
 */
@SuppressWarnings("CheckStyle")
@Getter
@Setter
@MappedSuperclass
public class CommonEntity implements Serializable{

    /** id.
     * The unique identifier for the entity.
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** uid.
     * The universally unique identifier (UID) for the entity.
     */
    @Column(name = "uid", nullable = false)
    private String uid;
}
