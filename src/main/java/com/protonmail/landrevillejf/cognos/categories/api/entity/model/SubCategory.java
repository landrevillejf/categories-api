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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subcategories")
public class SubCategory extends CommonEntity {

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
    @JsonIgnoreProperties("subcategories")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
