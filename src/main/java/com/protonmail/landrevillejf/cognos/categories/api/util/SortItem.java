package com.protonmail.landrevillejf.cognos.categories.api.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Represents a sorting item used for defining sorting criteria.
 */
@Getter
public class SortItem implements Serializable {

    /**
     * The field or property by which to sort the results.
     */
    @Schema(example = "id") // Set a default sorting property for Swagger
    private String field;

    /**
     * The sorting direction (ASCENDING or DESCENDING).
     */
    private Sort.Direction direction;
}

