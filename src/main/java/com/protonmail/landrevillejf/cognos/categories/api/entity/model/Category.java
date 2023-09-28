package com.protonmail.landrevillejf.cognos.categories.api.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.protonmail.landrevillejf.cognos.categories.api.entity.common.CommonEntity;
import jakarta.persistence.CascadeType;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends CommonEntity {

    //region Fields Entity
    /**
     *
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     *
     */
    @Column(name = "description")
    private String description;
    /**
     *
     */
    @JsonIgnoreProperties("category")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<SubCategory> subCategories = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     *
     * @param name
     * @param description
     */
    public Category(final String name, final String description) {
        super();
        this.name = name;
        this.description = description;
    }
}
