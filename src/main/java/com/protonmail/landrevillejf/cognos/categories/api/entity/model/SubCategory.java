/**
 * Represents a subcategory of e-learning courses.
 */
package com.protonmail.landrevillejf.cognos.categories.api.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@SuppressWarnings("CheckStyle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategories")
public class SubCategory extends CommonEntity {

    //region Fields Entity
    /**
     * The name of the subcategory.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the subcategory.
     */
    @Column(name = "description")
    private String description;

    /**
     * The category to which this subcategory belongs.
     */
    @JsonIgnoreProperties("subcategories")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /**
     * The timestamp when this subcategory was created.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this subcategory was last updated.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Creates a new Subcategory with the specified name and description.
     *
     * @param name        The name of the Subcategory.
     * @param description The description of the Subcategory.
     */
    public SubCategory(final String name, final String description) {
        super();
        this.name = name;
        this.description = description;
    }
}
