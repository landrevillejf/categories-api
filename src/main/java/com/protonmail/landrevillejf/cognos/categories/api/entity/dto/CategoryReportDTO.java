package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryReportDTO {

    private Long id;
    private String departmentCode;
    private String departmentName;
    private String departmentDescription;
    private Integer totalEmployees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
