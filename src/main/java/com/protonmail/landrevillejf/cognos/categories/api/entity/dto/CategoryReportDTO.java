package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryReportDTO {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String name;
    /**
     *
     */
    private String description;

    /**
     *
     */
    private LocalDateTime createdAt;
    /**
     *
     */
    private LocalDateTime updatedAt;
    /**
     *
     */
    private Integer totalSubcategories;
    /**
     *
     */
    private String createdAtFormatted;
    /**
     *
     */
    private String updatedAtFormatted;
    /**
     *
     */
    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createdAt.format(formatter);
    }
    /**
     *
     */
    public String getFormattedUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return updatedAt.format(formatter);
    }
}
