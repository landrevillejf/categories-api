package com.protonmail.landrevillejf.cognos.categories.api.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.protonmail.landrevillejf.cognos.categories.api.entity.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private String status;
    private List<ErrorDTO> errors;
    private T results;

}
