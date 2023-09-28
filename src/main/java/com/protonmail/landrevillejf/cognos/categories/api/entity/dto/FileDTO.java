package com.protonmail.landrevillejf.cognos.categories.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private String fileContent;
    private String fileName;

}
