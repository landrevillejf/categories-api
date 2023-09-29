/**
 * Represents a category for e-learning courses.
 */
package com.protonmail.landrevillejf.cognos.categories.api.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CheckStyle")
@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
        date = "2023-09-25",
        revision = 1,
        comments = "Author Category Entity"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends CommonEntity {

    //region Fields Entity
    /**
     * The name of the category.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the category.
     */
    @Column(name = "description")
    private String description;

    /**
     * The list of subcategories associated with this category.
     */
    @JsonIgnoreProperties("category")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<SubCategory> subCategories = new ArrayList<>();

    /**
     * The timestamp when this category was created.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when this category was last updated.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Creates a new Category with the specified name and description.
     *
     * @param name        The name of the category.
     * @param description The description of the category.
     */
    public Category(final String name, final String description) {
        super();
        this.name = name;
        this.description = description;
    }
}
