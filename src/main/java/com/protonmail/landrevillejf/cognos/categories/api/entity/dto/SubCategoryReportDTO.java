package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryReportDTO {

    private Long id;
    private String departmentCode;
    private String departmentName;
    private String departmentDescription;
    private Integer totalEmployees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
